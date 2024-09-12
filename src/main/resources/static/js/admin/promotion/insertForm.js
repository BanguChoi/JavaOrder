$("#promotionInsertBtn").on("click", function(){
	// 유효성 검사	
	if(!chkData("#promotionTitle", "제목을")) return;
	else if(!chkData("#promotionStartDate", "시작일을")) return;
	else if(!chkData("#promotionEndDate", "종료일을")) return;
	else if ($("#promotionEndDate").val() < $("#promotionStartDate").val()) {
				alert("종료일은 시작일보다 빠를 수 없습니다.");
				return;
	}
//	else if(!chkData("#promotionName", "작성자를")) return;
	//else if(!chkData("#file", "업로드할 이미지 파일을")) return; // 필수요소
	else{
//		if($("input[type='file']").val()!=""){			// 업로드할 이미지 파일 존재 시
//			if(!chkFile("input[type='file']")) return;	//이미지 파일만 업로드 가능 common에 선언
//		}
		
		actionProcess("#insertForm", "POST", "/admin/promotion/promotionInsert");
//		actionFileProcess("#insertForm", "POST", "/promotion/promotionInsert");
	}
});


$("#promotionCancelBtn").on("click", function(){
	$("#insertForm").each(function(){
		this.reset();	
	});
});


$("#promotionListBtn").on("click", function(){
	locationProcess("/admin/promotion/promotionList");
});


