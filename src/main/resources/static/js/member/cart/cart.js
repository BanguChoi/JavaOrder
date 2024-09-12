// 매장 선택 시
$(".hereBtn").click(function(){
	console.log("매장 식사 결제 진행");
	locationProcess("/pay/ready/"+0);	// 결제창 이동
});

// 포장 선택 시
$(".togoBtn").click(function(){
	console.log("테이크 아웃 결제 진행");
	locationProcess("/pay/ready/"+1);	// 결제창 이동
});