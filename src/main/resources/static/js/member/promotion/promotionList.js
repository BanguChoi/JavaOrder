// 제목 클릭 시 상세페이지 이동

// 글쓰기 버튼 없음

$(".page-item a").on("click", function(e){
	e.preventDefault();

	$("#page").val($(this).data("number"));
	actionProcess("#searchForm", "get", "/member/promotion/promotionList");
});