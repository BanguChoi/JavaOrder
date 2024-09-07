$(document).ready(function() {


	$(".productDetailBtn").on("click", function(){
		console.log(productId);
		locationProcess("/products/" + productId + "/detail");	
	});
	
	
	/* 카테고리 */
	$(document).on('click','.cateBtn a', function(e){
		e.preventDefault();
		var categoryCode = $(this).data('category');
	
		$("#searchForm input[name='category']").val(categoryCode);
		$("#searchForm input[name='page']").val(1);
		$("#keywordForm input[name='keyword']").val('');
		productLoad();
	})
	
	/* ALL 버튼 */
	$(document).on('click', '.allBtn', function(e){
		e.preventDefault();
		$("#searchForm input[name='category']").val('');
		$("#searchForm input[name='page']").val(1);
		$("#keywordForm input[name='keyword']").val('');
		
		productLoad();
	})
	
	
	/* 페이징 처리 */
	$(document).on("click", ".page-item a", function(e){
		e.preventDefault();
	    var pageNum = $(this).data("number");
	    $("#searchForm input[name='page']").val(pageNum);
			
		productLoad();
	});
	
	
	/* 검색 */
	$(document).on('click', '.searchBtn', function(e){
		e.preventDefault();
		
		console.log("검색버튼클릭");	
	
		var keyword = $("#searchForm input[name='keyword']").val();
		console.log("검색어:", keyword); 
		
		if(!chkData("#keyword", "제품명을")) return; 
		else {
			$("#searchForm input[name='page']").val(1);
			productLoad();	
		}
	});
	
	
	/* 카테고리, 검색, 페이징에 동일하게 사용해서 함수로 빼냄 */
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

});



