//로그인
$("#signInBtn").on("click", function(){
	location.href = "/member/signin";
});
//마이페이지
$('#userInfo').on('click',function(){

	location.href = "/member/mypage";
});

// 회원가입
$('#signUpBtn').click(function(){
	locationProcess("/member/signup");
})
	
$(document).ready(function(){
    fetch('/member/notices/latest')
        .then(response => response.json())
        .then(data => {
            const noticeLink = $('#latestNoticeLink');
            noticeLink.attr('href', '/member/notices/' + data.noticesNo); // href 속성 설정
            noticeLink.text(data.noticesTitle); // 텍스트 설정
        })
        .catch(error => {
            console.error('Error fetching latest notice:', error);
        });
});
