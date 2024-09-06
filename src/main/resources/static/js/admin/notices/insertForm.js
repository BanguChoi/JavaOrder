$("#noticesInsertBtn").on("click", function(){
	// 유효성 검사	
	if(!chkData("#noticesTitle", "제목을")) return;
	else if(!chkData("#noticesContent", "내용을")) return;
	//else if(!chkData("#file", "업로드할 이미지 파일을")) return; // 필수요소
//	else{
//		if($("#file").val()!=""){			// 업로드할 이미지 파일 존재 시
//			if(!chkFile("#file")) return;	//이미지 파일만 업로드 가능 common에 선언
//		}
		actionProcess("#insertForm", "POST", "/admin/notices/noticesInsert");
//		actionFileProcess("#insertForm", "POST", "/promotion/promotionInsert");
//	}
});


$("#noticesCancelBtn").on("click", function(){
	$("#insertForm").each(function(){
		this.reset();	
	});
});


$("#noticesListBtn").on("click", function(){
	locationProcess("/admin/notices/noticesList");
});

