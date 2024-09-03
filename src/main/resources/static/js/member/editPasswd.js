$(document).ready(function() {
    // 비밀번호 표시 기능
    $('#showCurrentPassword').change(function() {
        var type = $(this).is(':checked') ? 'text' : 'password';
        $('#currentPassword').attr('type', type);
    });

    $('#showNewPassword').change(function() {
        var type = $(this).is(':checked') ? 'text' : 'password';
        $('#newPassword').attr('type', type);
    });

    // 현재 비밀번호 확인
    $('#verifyPasswordBtn').click(function() {
        var currentPassword = $('#currentPassword').val();
        $.ajax({
            url: '/javaOrder/member/verifyPassword',
            method: 'POST',
            data: { currentPassword: currentPassword },
            success: function(response) {
                if (response.success) {
                    $('#newPassword').prop('disabled', false);
                    $('#newPasswordContainer').show();
                    $('#changePasswordBtn').show();
                    $('#currentPasswordError').text('');
                } else {
                    $('#currentPasswordError').text(response.message);
                    $('#newPassword').prop('disabled', true);
                    $('#newPasswordContainer').hide();
                    $('#changePasswordBtn').hide();
                }
            }
        });
    });

    // 비밀번호 변경
    $('#changePasswordBtn').click(function() {
        var currentPassword = $('#currentPassword').val();
        var newPassword = $('#newPassword').val();

        // 새 비밀번호 유효성 검사
        var passwordRegex = /^(?=.*[!@?.,_])[A-Za-z\d!?@.,_]{8,20}$/;
        if (!passwordRegex.test(newPassword)) {
            $('#passwordError').text('비밀번호는 8~20글자, 특수문자 1개 이상, 대소문자와 숫자를 사용해 주세요.');
            return;
        }

        $.ajax({
            url: '/javaOrder/member/changePassword',
            method: 'POST',
            data: {
                currentPassword: currentPassword,
                newPassword: newPassword
            },
            success: function(response) {
                if (response.success) {
                    alert('비밀번호가 성공적으로 변경되었습니다.');
                    // 비밀번호 변경 후 mypage로 리다이렉트
                    window.location.href = '/javaOrder/member/mypage';
                } else {
                    $('#passwordError').text(response.message);
                }
            }
        });
    });
});
