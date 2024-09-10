$(document).ready(function() {
    const $form = $('#loginForm');
    const $id = $form.find('input[name="adminId"]');
    const $password = $form.find('input[name="adminPassword"]');
	
	// 아이디와 비밀번호가 일치하지 않을 경우
	const $error = $('#error');
	    if ($error.length > 0 && $error.text().trim() !== "") {
	        alert($error.text());
	    }
		
    // 폼 제출 시 유효성 검사 수행
    $form.on('submit', function(event) {
        if (!chkData($id, '아이디') || !chkData($password, '비밀번호')) {
            event.preventDefault(); // 폼 제출 방지
        }
    });
});