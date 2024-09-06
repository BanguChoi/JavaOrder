$("#noticesUpdateBtn").on("click", function(){
	if(!chkData("#noticesTitle", "제목을")) return;
	else if(!chkData("#noticesContent", "내용을")) return;
//	else{	//File 추가시 사용
//		if($("#file").val()!=""){ // 업로드할 이미지 파일이 존재한다면
//이미지업로드시 풀기			if(!chkFile("#file")) return; // 이미지 파일만 업로드
//		}
	//	actionProcess("#updateForm", "post", "/promotion/promotionUpdate");
		actionFileProcess("#updateForm", "post", "/admin/notices/noticesUpdate");
//	}
});


$("#noticesCancelBtn").on("click", function(){
	$("#updateForm").each(function(){
		this.reset();
	});
});


$("#noticesListBtn").on("click", function(){
	locationProcess("/admin/notices/noticesList");
});