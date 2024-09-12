// 제목 클릭 시 상세페이지 이동


// 페이징 처리 실행안되는 상황
$(".page-item a").on("click", function(e){
	e.preventDefault();

	$("#page").val($(this).data("number"));
	actionProcess("#searchForm", "get", "/member/notices/noticesList");
});