$("#productDetailBtn").on("click", function(){
	locationProcess("/product/" + productId);
})

$('.allBtn').on("click", function(){
	location.href('/product/productList');
})

$('.coffeeBtn, .nonCoffeeBtn, etcBtn').on('click', function(){
	var category = "";
	
	if($(this).hasClass('coffeeBtn')) {
		category = 'A';
	} else if($(this).hasClass('nonCoffeeBtn')) {
		category = 'B';
	} else if($(this).hasClass('etcBtn')) {
		category = 'C';
	} 
	
	
	$.ajax({
		url: '/product/productList',
		method: 'GET',
		data: {
			category: category,
		},
		success: function(response) {
			 updateProductList(response);
		},
		error: function(xhr, status, error) {
            console.error('Error fetching products:', error);
        }
	});
});

function updateProductList(products) {

    var $productContainer = $('.container.d-flex.justify-content-center');
    $productContainer.empty(); // 기존 제품 목록 비우기
    
    if (products.length === 0) {
        $productContainer.html('<div class="text-center">등록된 제품이 없습니다.</div>');
    } else {
        products.forEach(function(product) {
            var productHtml = `
                <div data-no="${product.productId}">
                    <div class="card mx-2" style="width: 14rem;">
                        <img class="card-img-top" src="${product.imageSrc}">
                        <div class="card-body">
                            <h5 class="card-title">${product.productName}</h5>
                            <p class="card-text">${product.productPrice}원</p>
                            <a href="/product/${product.productId}" class="btn btn-primary productDetailBtn">제품담기</a>
                        </div>
                    </div>
                </div>
            `;
            $productContainer.append(productHtml);
        });
    }
	
}