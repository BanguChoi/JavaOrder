$("#promotionInsertBtn").on("click", function(){
	// 유효성 검사	
	if(!chkData("#promTitle", "제목을")) return;
	else if(!chkData("#promContent", "내용을")) return;
	else if(!chkData("#promName", "작성자를")) return;
	//else if(!chkData("#file", "업로드할 이미지 파일을")) return; // 필수요소
//	else{
//		if($("#file").val()!=""){			// 업로드할 이미지 파일 존재 시
//			if(!chkFile("#file")) return;	//이미지 파일만 업로드 가능 common에 선언
//		}
		actionProcess("#insertForm", "POST", "/adminPromotion/promotionInsert");
//		actionFileProcess("#insertForm", "POST", "/promotion/promotionInsert");
//	}
});


$("#promotionCancelBtn").on("click", function(){
	$("#insertForm").each(function(){
		this.reset();	
	});
});


$("#promotionListBtn").on("click", function(){
	locationProcess("/adminPromotion/promotionList");
});

