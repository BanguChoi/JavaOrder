// 제목 클릭 시 상세페이지 이동

// 글쓰기 버튼
    $("#insertFormBtn").on("click", function(){
        locationProcess("/admin/notices/insertForm");
    });

// 페이징 처리 실행안되는 상황
$(".page-item a").on("click", function(e){
	e.preventDefault();

	$("#page").val($(this).data("number"));
	actionProcess("#searchForm", "get", "/admin/notices/noticesList");
});