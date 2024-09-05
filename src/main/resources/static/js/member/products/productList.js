$(".productDetailBtn").on("click", function(){
	console.log(productId);
	locationProcess("/products/" + productId + "/detail");	
});

/* 페이징 처리 */
$(document).on("click", ".page-item a", function(e){
	e.preventDefault();
    var pageNum = $(this).data("number");
    $("#page").val(pageNum);
	productLoad();
	
});


/* 카테고리 */
$('.cateBtn').on('click', function(){
	var categoryCode = $(this).attr('id') || 'ALL';
	$('#categoryCode').val(categoryCode);
	$("#page").val(1);
	productLoad();
})

/* 검색 */











/* 카테고리, 페이징, 검색에 동일하게 사용해서 함수로 빼냄 */
function productLoad() {
	$("#searchForm").attr({
	        "method": "get",
	        "action": "/products/productList"
	    });
		
		$.ajax({
	        url: $("#searchForm").attr("action"),
	        type: $("#searchForm").attr("method"),
	        data: $("#searchForm").serialize(),
	        success: function(response) {
	            $('#productListContainer').html(response);
	        },
	        error: function(xhr, status, error) {
	            console.error("AJAX Error:");
	            console.error("Status:", status);
	            console.error("Error:", error);
	            console.error("Response Text:", xhr.responseText);
	            console.error("Status Code:", xhr.status);
	        }
	    });
}




