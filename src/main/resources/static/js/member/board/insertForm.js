$("#boardInsertBtn").on("click", function(){
	// 유효성 검사
	if(!chkData("#boardTitle", "제목을")) return;
	else if(!chkData("#boardContent", "내용을")) return;
	else if(!chkData("#boardPasswd", "비밀번호를")) return;
	
		
		actionProcess("#insertForm", "POST", "/member/board/boardInsert");

});

$("#boardCancelBtn").on("click", function(){
	$("#insertForm").each(function(){
		this.reset();
	});
});

$("#boardListBtn").on("click", function(){
	locationProcess("/member/board/boardList");
})