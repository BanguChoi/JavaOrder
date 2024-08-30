// 제목 클릭 시 상세페이지 이동
$(".goDetail").on("click", function(){
	// let no = $(this).parents("tr").attr("data-no");
	let promCode = $(this).parents("tr").data("no");
	console.log("글번호 : "+promCode);
	
	locationProcess("/adminPromotion/"+promCode);
});


// 글쓰기 버튼
    $("#insertFormBtn").on("click", function(){
        console.log("클릭");  // 확인용
        locationProcess("/adminPromotion/insertForm");
    });


// 페이징 처리 실행안되는 상황
$(".page-item a").on("click", function(e){
	e.preventDefault();

	$("#page").val($(this).data("number"));
	actionProcess("#searchForm", "get", "/board/boardList");
});