$("#noticesInsertBtn").on("click", function(){
	// 유효성 검사	
	if(!chkData("#noticesTitle", "제목을")) return;
	else if(!chkData("#noticesContent", "내용을")) return;
	else {actionProcess("#insertForm", "POST", "/admin/notices/noticesInsert");}
});


$("#noticesCancelBtn").on("click", function(){
	$("#insertForm").each(function(){
		this.reset();	
	});
});


$("#noticesListBtn").on("click", function(){
	locationProcess("/admin/notices/noticesList");
});

