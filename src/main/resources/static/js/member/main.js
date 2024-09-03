//로그인
$("#signInBtn").on("click", function(){
	location.href = "/javaOrder/member/signin";
});

// 회원가입
$("#signUpBtn").on("click", function(){
	location.href = "/javaOrder/member/signup";	
});

//마이페이지
$('#userInfo').on('click',function(){
	location.href = "/javaOrder/member/mypage";
});