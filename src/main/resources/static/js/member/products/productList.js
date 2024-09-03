$("#productDetailBtn").on("click", function(){
	locationProcess("/products/" + productId + "/detail");	
});

/* 페이징 처리 */
$(document).on("click", '.page-item a, #productListContainer', function(e){
	e.preventDefault();
    var pageNum = $(this).data("number");
    $("#page").val(pageNum);
	
	$.ajax({
		url: "/products/productList",
		method: "GET",
		data: $('#searchForm').serialize(),
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



