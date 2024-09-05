$(document).ready(function() {
    // 비밀번호 표시 기능
    $('#showCurrentPassword').change(function() {
        const type = $(this).is(':checked') ? 'text' : 'password';
        $('#currentPassword').attr('type', type);
    });

    $('#showNewPassword').change(function() {
        const type = $(this).is(':checked') ? 'text' : 'password';
        $('#newPassword').attr('type', type);
    });

    // 현재 비밀번호 확인
    $('#verifyPasswordBtn').click(function() {
        const currentPassword = $('#currentPassword').val();
        $.ajax({
            url: '/member/verifyPassword',
            method: 'POST',
            data: { currentPassword: currentPassword },
            success: function(response) {
                if (response.success) {
                    $('#newPassword').prop('readonly', false); // 새 비밀번호 입력 가능 변경
                    $('#changePasswordBtn').show(); // 비밀번호 변경 버튼 표시
					$('#verifyPasswordBtn').hide(); // 현재 비밀번호 확인 버튼 숨김
                    $('#currentPasswordError').text(''); // 현재 비밀번호 값 지우기
					$('#successMessage').text('비밀번호가 일치합니다.');
                } else {
                    $('#currentPasswordError').text(response.message);
                    $('#newPassword').prop('readonly', true);
                    $('#changePasswordBtn').hide();
					$('#successMessage').text('');
                }
            }
        });
    });

    // 비밀번호 변경
    $('#changePasswordBtn').click(function() {
        const currentPassword = $('#currentPassword').val();
        const newPassword = $('#newPassword').val();

        // 새 비밀번호 유효성 검사
        const passwordRegex = /^(?=.*[!@?.,_])[A-Za-z\d!?@.,_]{8,20}$/;
        if (!passwordRegex.test(newPassword)) {
            $('#passwordError').text('비밀번호는 8~20글자, 특수문자 1개 이상, 대소문자와 숫자를 사용해 주세요.');
            return;
        }

        $.ajax({
            url: '/member/changePassword',
            method: 'POST',
            data: {
                currentPassword: currentPassword,
                newPassword: newPassword
            },
            success: function(response) {
                if (response.success) {
                    alert('비밀번호가 성공적으로 변경되었습니다.');
                    // 비밀번호 변경 후 mypage로 리다이렉트
                    location.href = '/member/mypage';
                } else {
                    $('#passwordError').text(response.message);
                }
            }
        });
    });
	
	$('#cancelBtn').click(function(){
		location.href = '/member/mypage';
		})
});