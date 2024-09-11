$(document).ready(function() {
    // 회원가입 버튼 클릭 시 처리
    $('#signupBtn').on('click', function(event) {
        event.preventDefault(); // 기본 제출 동작 방지

        // 입력 필드 값 가져오기
        const name = $('#name');
        const id = $('#id');
        const password = $('#password');
        const email = $('#email');
        const phone = $('#phone');
        const address = $('#address');
        const birth = $('#birth');

        // 유효성 검사
        if (!checkForm(name, "이름") || !checkForm(id, "아이디") || !validateId(id) ||
            !validatePassword(password) || !checkForm(email, "이메일") ||
            !validateEmail(email) || !checkForm(phone, "전화번호") || !validatePhone(phone) ||
            !checkForm(address, "주소") || !checkForm(birth, "생년월일")) {
            return;
        }
		
		// 아이디 중복 체크
        // AJAX 요청을 통해 서버로 데이터 전송
        $.ajax({
			type:'POST',
			url:'/member/checkMemberId',
			data:{ memberId: id.val().trim()},
			success: function(response){
				if(response.exists){
					$('#idError').text('이 ID는 이미 사용 중입니다.');
					$('#idError').css('color','red');
				} else {
					$.ajax({
					    type: 'POST',
					    url: '/member/signup',
					    data: {
					        memberName: name.val().trim(),
					        memberId: id.val().trim(),
					        memberPasswd: password.val(),
					        memberEmail: email.val().trim(),
					        memberPhone: phone.val().trim(), // 포맷 후 저장
					        memberAddress: address.val().trim(),
					        memberBirth: birth.val(),
					        memberDate: getDateFormat(new Date()), // 가입일 자동 처리
							memberLast: getDateFormat(new Date()),
					        memberStatus: 'M' // 상태값 자동 설정
					    },
					success:function() {
					    // 성공 시 로그인 화면으로 리다이렉트
					    location.href = '/member/signin';
						},
					error: function() {
					    $('#error').text('회원가입에 실패했습니다: ');
						}
					});
				}
		    },
			error: function(){
				$('#idError').text('ID 중복 확인 중 오류가 발생했습니다.');
				$('#idError').css('color','red');
			}
		});
	});

    // 주소 검색 버튼 클릭 시 처리
    $('#addressSearchBtn').on('click', function() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색된 주소를 입력 필드에 설정
                $('#address').val(data.roadAddress);
                $('#addressError').text(''); // 오류 메시지 제거
            }
        }).open();
    });

    // 취소 버튼 클릭 시 처리
    $('#resetButton').on('click', function() {
        window.location.href = '/';
    });

    // 전화번호 자동 포맷 함수
	$('#phone').on('input', function() {
       let phoneValue = $(this).val().replace(/\D/g, ''); // 숫자 외의 모든 문자 제거

       // 전화번호 길이에 따른 하이폰 추가
       if (phoneValue.length > 11) {
           phoneValue = phoneValue.slice(0, 11); // 최대 11자리로 제한
       }

       let formattedPhone = '';
       if (phoneValue.length > 7) {
           formattedPhone = phoneValue.replace(/^(\d{3})(\d{4})(\d{0,4})$/, '$1-$2-$3');
       } else if (phoneValue.length > 3) {
           formattedPhone = phoneValue.replace(/^(\d{3})(\d{0,4})$/, '$1-$2');
       } else {
           formattedPhone = phoneValue;
       }

       $(this).val(formattedPhone);
	   
        // 유효성 검사
        if (phoneValue.length < 10 || phoneValue.length > 11) {
            $('#phoneError').text('전화번호는 10자리 또는 11자리 숫자만 포함할 수 있습니다.');
        } else {
            $('#phoneError').text('');
        }
    });

    // 비밀번호 표시 기능
    $('#showPassword').on('change', function() {
        const isChecked = $(this).is(':checked');
        const passwordField = $('#password');

        if (isChecked) {
            passwordField.attr('type', 'text');
        } else {
            passwordField.attr('type', 'password');
        }
    });

    $('#password').on('focus', function() {
        $('#passwordError').text('');
    });
	
	$('#name, #id, #password, #email, #phone, #address, #birth').on('input', function() {
        const errorDiv = $(`#${this.id}Error`);
        errorDiv.text('');
    });
});

// ID 유효성 검사 함수
function validateId(id) {
    const idValue = id.val().trim();
    if (idValue === "") return false;
    // ID는 대소문자와 숫자만 허용
    const idRegex = /^[a-zA-Z0-9]{6,12}$/;
    const isValid = idRegex.test(idValue);
    const errorDiv = $('#idError');
    errorDiv.text(isValid ? '' : "아이디는 6~12글자 대소문자와 숫자만 포함할 수 있습니다.");
    return isValid;
}

// 비밀번호 유효성 검사 함수
function validatePassword(password) {
    const passwordValue = password.val();
    const passwordRegex = /^(?=.*[!@?.,_])[A-Za-z\d!?@.,_+\-$%^&*]{8,20}$/;
    const isValid = passwordRegex.test(passwordValue);
    const passwordErrorDiv = $('#passwordError');

    if (isValid) {
        passwordErrorDiv.text('');
    } else {
        passwordErrorDiv.text("비밀번호는 8~20글자, 대소문자, 숫자 및 특수문자(!@?.,_)를 포함해야 합니다.");
    }
    return isValid;
}

// 이메일 유효성 검사 함수
function validateEmail(email) {
    const emailValue = email.val().trim();
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const isValid = emailRegex.test(emailValue);
    const errorDiv = $('#emailError');
    errorDiv.text(isValid ? '' : "유효한 이메일 주소를 입력해 주세요.");
    return isValid;
}

// 전화번호 유효성 검사 함수
function validatePhone(phone) {
    const phoneValue = phone.val().trim().replace(/\D/g, ''); // 숫자만 추출
    const phoneRegex = /^\d{10,11}$/; // 10자리 또는 11자리 전화번호 허용
    const isValid = phoneRegex.test(phoneValue);
    const errorDiv = $('#phoneError');
    errorDiv.text(isValid ? '' : "전화번호는 10자리 또는 11자리 숫자만 포함할 수 있습니다.");
    return isValid;
}

// 폼 필드가 비어있거나 유효성 검사를 통과하지 못할 경우
function checkForm(field, fieldName) {
    if (field.val().trim() === "") {
        $(`#${field.attr('id')}Error`).text(`${fieldName}을(를) 입력해 주세요.`);
        return false;
    }
    $(`#${field.attr('id')}Error`).text('');
    return true;
}