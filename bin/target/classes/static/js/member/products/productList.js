$("#productDetailBtn").on("click", function(){
	locationProcess("/products/" + productId + "/detail");	
});

/* 페이징 처리 */
$(document).on("click", ".page-item a", function(e){
	e.preventDefault();
    var pageNum = $(this).data("number");
    $("#page").val(pageNum);
		
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
});

/* 카테고리 */









