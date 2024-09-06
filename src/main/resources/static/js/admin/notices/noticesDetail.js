
/* 수정버튼 클릭 */
$("#updateFormBtn").on("click", function(){
	actionProcess("#dataForm", "post", "/admin/notices/updateForm");
	/*pwdInit("visible");
	btnInit();
	buttonCheck = "updateButton";*/
});


/* 삭제버튼 클릭 시 처리 이벤트*/
$("#noticesDeleteBtn").on("click", function(){
	if(confirm("정말 삭제하시겠습니까?")){
		actionProcess("#dataForm", "post", "/admin/notices/noticesDelete");
	}
});


/* 등록 버튼 클릭 시 처리 버튼 */
$("#insertFormBtn").click(function(){
	locationProcess("/admin/notices/insertForm");
});


/*  목록 버튼 클릭 시 처리 이벤트 */
$("#noticesListBtn").click(function(){
	locationProcess("/admin/notices/noticesList");
});