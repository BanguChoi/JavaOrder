// 글쓰기 버튼
$("#insertFormBtn").on("click", function(){
	locationProcess("/member/board/insertForm");
});


// 페이징 처리 
$(".page-item a").on("click", function(e){
	e.preventDefault();
	/*$("#searchForm").find("input[name='pageNum']").val($(this).attr("href"));*/
	$("#page").val($(this).data("number"));
	actionProcess("#searchForm", "get", "/member/board/boardList");
});