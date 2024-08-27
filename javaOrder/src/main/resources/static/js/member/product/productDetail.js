

/* 제품 옵션 변경 시 가격 자동반영 */
$(document).ready(function(){
	/* - + 버튼 클릭 */
	$('#minusBtn').click(function(){
		updateCount('#count', -1);
		calcTotalPrice();
	});

	$('#plusBtn').click(function(){
		updateCount('#count', 1);
		calcTotalPrice();
	});
	
	
	let basePrice = 0;
	
	function productPrice() {
		$.ajax({
			url: "/product/totalPrice",
			method: "GET",
			data: {
				productId: productId
			},
			success: function(price){
				basePrice = price;
				calcTotalPrice();
			},
			error: function(error) {
				console.log('가격을 가져오는 중 오류 발생:', error);
			}
		});
	}
	
	function calcTotalPrice() {
		let calcPrice = basePrice;
		let count = parseInt($('#count').val(), 10);
		
		// 사이즈 옵션
		let sizePrice = 0;
		if ($('#sizeM').is(':checked')) {
            sizePrice = 700;
        } else if ($('#sizeL').is(':checked')) {
            sizePrice = 1400;
        }
        calcPrice += sizePrice;

        // 샷 옵션
        let shotPrice = 0;
        let shotCount = $('#optionShot').val();
        if (shotCount !== "추가안함") {
            shotPrice = parseInt(shotCount) * 500;
        }
        calcPrice += shotPrice;

        // 시럽 옵션
        if ($('#caramel').is(':checked')) {
            calcPrice += 500;
        }
        if ($('#vanila').is(':checked')) {
            calcPrice += 500;
        }
		
		// 수량 옵션
		calcPrice *= count;
		
        // 최종 가격
        $('#total-price').text(calcPrice.toLocaleString());
    }

    // 이벤트 리스너 등록
    $('input[type=radio], input[type=checkbox], input[type=text], select').change(function() {
        calcTotalPrice();
    });
	
	// 페이지 로드 시 초기 가격 불러오기
    const productId = $('input[name="productId"]').val(); // hidden input에서 productId 가져오기
    productPrice(productId);
	
});


/* 장바구니 담기 버튼 클릭 시 cartitem으로 form 전송 */
$('#addCartBtn').on("click", function() {
	$.ajax({
		
	})
})



