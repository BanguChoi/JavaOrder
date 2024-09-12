$(document).ready(function() {
    // 필드의 원래 값으로 초기화
	function resetField(fieldId){
		const inputField = $(`#${fieldId}`);
		const originalValue = inputField.data('original-value');
		if(originalValue !== undefined) {
			inputField.val(originalValue)
		}
		$(`#${fieldId}Error`).text('');
		disableEditing(fieldId);
	}
	
	// 각 필드를 수정 모드로 변경
    function editField(fieldId) {
        const inputField = $(`#${fieldId}`);
        const changeButton = inputField.siblings('button.btn-success');
        const editButton = inputField.siblings('button.updateBtn');
        const searchButton = inputField.siblings('button.btn-secondary');
		const cancelButton = inputField.siblings('button.cancelBtn');
		
		if(!inputField.data('original-value')){
			inputField.data('original-value',inputField.val());
		}
		
        inputField.prop('readonly', false);
        changeButton.show();
		cancelButton.show();
        editButton.hide();
        if (fieldId === 'memberAddress') {
            searchButton.show();
        }
    }

    // 각 필드를 읽기 전용 모드로 변경
    function disableEditing(fieldId) {
        const inputField = $(`#${fieldId}`);
        const changeButton = inputField.siblings('button.btn-success');
        const editButton = inputField.siblings('button.updateBtn');
        const searchButton = inputField.siblings('button.btn-secondary');
		const cancelButton = inputField.siblings('button.cancelBtn');
				
        inputField.prop('readonly', true);
        changeButton.hide();
		cancelButton.hide();
        editButton.show();
        if (fieldId === 'memberAddress') {
            searchButton.hide();
        }
    }

    // 유효성 검사 함수
    function validateField(fieldId) {
        const field = $(`#${fieldId}`);
        const value = field.val().trim();
        const errorDiv = $(`#${fieldId}Error`);
        
        // 필드가 공백이 아닌 경우 유효성 검사 수행
        if (['memberName', 'memberId', 'memberEmail', 'memberPhone', 'memberAddress', 'memberBirth'].includes(fieldId)) {
            if (value === '' ) {
                errorDiv.text('이 필드는 비워둘 수 없습니다.');
                return false;
            }
        }

        if (fieldId === 'memberId') {
            const idRegex = /^[a-zA-Z0-9]{6,12}$/;
            const isValid = idRegex.test(value);
            errorDiv.text(isValid ? '' : '아이디는 6~12글자 대소문자와 숫자만 포함할 수 있습니다.');
            return isValid;
        }

        if (fieldId === 'memberEmail') {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            const isValid = emailRegex.test(value);
            errorDiv.text(isValid ? '' : '유효한 이메일 주소를 입력해 주세요.');
            return isValid;
        }

        if (fieldId === 'memberPhone') {
            const phoneValue = value.replace(/\D/g, ''); // 숫자만 추출
            const phoneRegex = /^\d{10,11}$/; // 10자리 또는 11자리 전화번호 허용
            const isValid = phoneRegex.test(phoneValue);
            errorDiv.text(isValid ? '' : '전화번호는 10자리 또는 11자리 숫자만 포함할 수 있습니다.');
            return isValid;
        }

        if (fieldId === 'memberAddress' || fieldId === 'memberBirth') {
            // 주소와 생일은 비어 있는지 확인만 함
            const isValid = value !== '';
            errorDiv.text(isValid ? '' : '이 필드는 비워둘 수 없습니다.');
            return isValid;
        }

        return true;
    }

    // 필드 값 업데이트
    function updateField(fieldId) {
        if (!validateField(fieldId)) return; // 유효성 검사 실패 시 처리 중지
        
		if(fieldId === 'memberId'){
		//아이디 중복 검사
		const idValue = $(`#${fieldId}`).val().trim();
		$.ajax({
			url: '/member/checkMemberId',
			method: 'POST',
			data: {memberId: idValue },
			success: function(response){
				if (response.exists) {
                    $('#memberIdError').text('이 ID는 이미 사용 중입니다.');
                } else {
						sendUpdateRequest(fieldId);
                	}
				},
			error: function() {
				$('#memberIdError').text('아이디 중복 확인 중 오류가 발생하였습니다.');
			}		
		});
	}else{
		sendUpdateRequest(fieldId);
		}
	}

	function sendUpdateRequest(fieldId){
	    const inputField = $(`#${fieldId}`);
	    const newValue = inputField.val().trim();
	    const memberCode = $('#memberCode').val();
	    
	    $.ajax({
	        url: '/member/updateField',
	        method: 'POST',
	        data: {
	            memberCode: memberCode,
	            fieldId: fieldId,
	            newValue: newValue
	        },
	        success: function() {
	            // 성공 시 읽기 전용 모드로 전환
	            disableEditing(fieldId);
	            alert('변경이 완료되었습니다.');
	        },
	        error: function() {
	            alert('변경 중 오류가 발생했습니다.');
	        }
	    });
	}

    // 주소 검색
    function searchAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
                const fullAddress = data.roadAddress;
                $('#memberAddress').val(fullAddress);
                $('#memberAddressError').text(''); // 오류 메시지 제거
            }
        }).open();
    }
	
	// 수정 취소 버튼
	$('button.cancelBtn').on('click', function() {
       const fieldId = $(this).siblings('input').attr('id');
       resetField(fieldId);
   });
	   
    // 입력 필드 수정
    $('button.updateBtn').on('click', function() {
        const fieldId = $(this).siblings('input').attr('id');
        editField(fieldId);
    });

    // 수정 필드 전송
    $('button.btn-success').on('click', function() {
        const fieldId = $(this).siblings('input').attr('id');
        updateField(fieldId);
    });

    // 비밀번호 수정 페이지로 이동
    $('#changePasswordBtn').on('click', function() {
        location.href = '/member/editPasswd';
    });

    // 주소 검색 버튼 클릭 시 처리
    $('#searchAddressBtn').on('click', function() {
        searchAddress();
    });

    // 폼 필드 값 변경 시 오류 메시지 제거
    $('#memberName, #memberId, #memberEmail, #memberPhone, #memberAddress, #memberBirth').on('input', function() {
        const errorDiv = $(`#${this.id}Error`);
        errorDiv.text('');
    });
	
	// 회원 탈퇴
	$('#deleteAccountBtn').on('click', function() {
        const memberCode = $('#memberCode').val();

        if (confirm('정말로 회원 탈퇴를 하시겠습니까?')) {
            $.ajax({
                url: '/member/delete',
                method: 'POST',
                data: { memberCode: memberCode },
                success: function(response) {
                    alert(response);
                    if (response.includes("완료되었습니다.")) {
                        window.location.href = '/'; // 메인 페이지로 리다이렉트
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('AJAX 요청 중 오류 발생:', textStatus, errorThrown);
                    alert('회원 탈퇴 중 오류가 발생했습니다.');
                }
            });
        }
    });	
});