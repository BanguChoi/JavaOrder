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
