$("#boardInsertBtn").on("click", function(){
	// 유효성 검사
	if(!chkData("#boardTitle", "제목을")) return;
	else if(!chkData("#boardContent", "내용을")) return;
	else if(!chkData("#boardPasswd", "비밀번호를")) return;
	//else if(!chkData("#file", "업로드할 이미지 파일을")) return; // 필수요소
//	else{
//		if($("#file").val()!=""){			// 업로드할 이미지 파일 존재 시
//			if(!chkFile("#file")) return;	//이미지 파일만 업로드 가능
//		}
		
		actionProcess("#insertForm", "POST", "/memberBoard/boardInsert");
//		actionFileProcess("#insertForm", "POST", "/board/boardInsert");
//	}
});

$("#boardCancelBtn").on("click", function(){
	$("#insertForm").each(function(){
		this.reset();
	});
});

$("#boardListBtn").on("click", function(){
	locationProcess("/memberBoard/boardList");
})