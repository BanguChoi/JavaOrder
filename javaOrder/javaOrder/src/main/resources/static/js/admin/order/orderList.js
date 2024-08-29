// 상태 설정
$(".statusBtn").click(function(){
	let no = $(this).parents("tr").data("no");
	let st = $(this).val();
			
	locationProcess(`/javaOrder/admin/updateOrders/${no}/${st}`);
})
