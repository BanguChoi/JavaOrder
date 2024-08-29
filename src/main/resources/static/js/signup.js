$(document).ready(function() {
    // 회원가입 버튼 클릭 시 처리
    $('#signupBtn').on('click', function(event) {
        event.preventDefault(); // 기본 제출 동작 방지

        // 입력 필드 값 가져오기
        const name = $('#name');
        const id = $('#id');
        const password = $('#password');
        const confirmPassword = $('#confirmPassword');
        const email = $('#email');
        const phone = $('#phone');
        const address = $('#address');
        const birth = $('#birth');

        // 유효성 검사
        if (!checkForm(name, "이름") || !checkForm(id, "아이디") || !validateId(id) ||
            !validatePassword(password, confirmPassword) || !checkForm(email, "이메일") ||
            !validateEmail(email) || !checkForm(phone, "전화번호") || !validatePhone(phone) ||
            !checkForm(address, "주소") || !checkForm(birth, "생년월일")) {
            return;
        }

        // AJAX 요청을 통해 서버로 데이터 전송
        $.ajax({
            type: 'POST',
            url: '/javaOrder/member/signup',
            data: {
                memberName: name.val().trim(),
                memberId: id.val().trim(),
                memberPasswd: password.val(),
                memberEmail: email.val().trim(),
                memberPhone: phone.val().trim().replace(/\D/g, ''), // 포맷 제거 후 저장
                memberAddress: address.val().trim(),
                memberBirth: birth.val(),
                memberDate: getDateFormat(new Date()), // 가입일 자동 처리
                memberStatus: 'M' // 상태값 자동 설정
            }
        }).done(function(response) {
            // 성공 시 메인 화면으로 리다이렉트
            window.location.href = '/javaOrder/main';
        }).fail(function(jqXHR, textStatus, errorThrown) {
            $('#error').text('회원가입에 실패했습니다: ' + textStatus);
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
        window.location.href = '/javaOrder/main';
    });

    // 전화번호 자동 포맷 함수
    $('#phone').on('input', function() {
        const phoneValue = $(this).val().replace(/\D/g, ''); // 숫자 외의 모든 문자 제거
        let formattedPhone = '';
        if (phoneValue.length > 6) {
            formattedPhone = phoneValue.replace(/^(\d{3})(\d{4})(\d{4})$/, '$1-$2-$3');
        } else if (phoneValue.length > 3) {
            formattedPhone = phoneValue.replace(/^(\d{3})(\d{0,4})$/, '$1-$2');
        } else {
            formattedPhone = phoneValue;
        }
        $(this).val(formattedPhone); // 입력 필드에 포맷 적용
    });
});

// 공백 검사 함수
function checkForm(item, msg) {
    const $item = $(item);
    const errorDiv = $item.siblings('.error-message');
    const message = $item.val().trim() === "" ? `${msg} 입력해 주세요.` : "";
    errorDiv.text(message); // div에 오류 메시지 설정
    return !message;
}

// ID 유효성 검사 함수
function validateId(id) {
    const idValue = id.val().trim();
    if (idValue === "") return false;
    // ID는 대소문자와 숫자만 허용
    const idRegex = /^[a-zA-Z0-9]{6,12}$/;
    const isValid = idRegex.test(idValue);
    const errorDiv = $('#id').siblings('.error-message');
    errorDiv.text(isValid ? '' : "아이디는 6~12글자 대소문자와 숫자만 포함할 수 있습니다.");
    return isValid;
}

// 비밀번호 유효성 검사 함수
function validatePassword(password, confirmPassword) {
    const passwordValue = password.val();
    const confirmPasswordValue = confirmPassword.val();
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@?.,_])[A-Za-z\d!@?.,_]{8,20}$/;
    const isValid = passwordRegex.test(passwordValue) && passwordValue === confirmPasswordValue;
    const passwordErrorDiv = $('#password').siblings('.error-message');
    const confirmPasswordErrorDiv = $('#confirmPassword').siblings('.error-message');
    passwordErrorDiv.text(isValid ? '' : "비밀번호는 8~20글자, 대소문자, 숫자 및 특수문자(!@?.,_)를 포함해야 합니다.");
    confirmPasswordErrorDiv.text(isValid ? '' : "비밀번호 확인이 일치하지 않습니다.");
    return isValid;
}

// 이메일 유효성 검사 함수
function validateEmail(email) {
    const emailValue = email.val().trim();
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const isValid = emailRegex.test(emailValue);
    const errorDiv = $('#email').siblings('.error-message');
    errorDiv.text(isValid ? '' : "유효한 이메일 주소를 입력해 주세요.");
    return isValid;
}

// 전화번호 유효성 검사 함수
function validatePhone(phone) {
    const phoneValue = phone.val().trim().replace(/\D/g, ''); // 숫자만 추출
    const phoneRegex = /^\d{10,11}$/; // 10자리 또는 11자리 전화번호 허용
    const isValid = phoneRegex.test(phoneValue);
    const errorDiv = $('#phone').siblings('.error-message');
    errorDiv.text(isValid ? '' : "전화번호는 10자리 또는 11자리 숫자만 포함할 수 있습니다.");
    return isValid;
}