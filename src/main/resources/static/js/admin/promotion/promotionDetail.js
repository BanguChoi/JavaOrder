
/* 수정버튼 클릭 */
$("#updateFormBtn").on("click", function(){
	actionProcess("#dataForm", "post", "/adminPromotion/updateForm");
	/*pwdInit("visible");
	btnInit();
	buttonCheck = "updateButton";*/
});


/* 삭제버튼 클릭 시 처리 이벤트*/
$("#promotionDeleteBtn").on("click", function(){
	if(confirm("정말 삭제하시겠습니까?")){
		actionProcess("#dataForm", "post", "/adminPromotion/promotionDelete");
	}
});


/* 등록 버튼 클릭 시 처리 버튼 */
$("#insertFormBtn").click(function(){
	locationProcess("/adminPromotion/insertForm");
});


/*  목록 버튼 클릭 시 처리 이벤트 */
$("#promotionListBtn").click(function(){
	locationProcess("/adminPromotion/promotionList");
});