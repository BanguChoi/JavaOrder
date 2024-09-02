$(document).ready(function() {
    // 수정 버튼 클릭 시 필드를 활성화
    $('.btn-edit').click(function() {
        const fieldId = $(this).data('field');
        if (fieldId) {
            $('#' + fieldId).removeAttr('readonly');
        }
		
    });
/*
    // 인증 이메일 전송 버튼 클릭 시
    $('#sendVerificationEmail').click(function() {
        const newEmail = $('#newEmail').val();
        if (validateEmail(newEmail)) {
            // 이메일 인증 API 호출 시뮬레이션
            console.log('Sending verification email to:', newEmail);
            $('#verificationMessage').text('인증 이메일이 전송되었습니다. 이메일을 확인해 주세요.');
        } else {
            $('#verificationMessage').text('유효한 이메일 주소를 입력해 주세요.');
        }
    });

    // 이메일 유효성 검증 함수
    function validateEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }	
*/
});
