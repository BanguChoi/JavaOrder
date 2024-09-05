

/* 제품 옵션 변경 시 가격 자동반영 */
$(document).ready(function(){

  	// 기본 옵션 설정
 	 $('input[name="optionSize"][value="1"]').prop('checked', true);
	 $('input[name="optionTemperature"][value="1"]').prop('checked', true);
 	 $("#optionShot").val("추가안함");


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
	const productId = $('input[name="productId"]').val();
	
	
	function productPrice() {
		$.ajax({
			url: "/products/totalPrice",
			method: "GET",
			data: {
				productId: productId
			},
			success: function(price){
				basePrice = price;
				calcTotalPrice();
				console.log("가격 가져오기 성공");
			},
			error: function(error) {
				console.log('가격을 가져오는 중 오류 발생:', error);
			}
		});
	}
	
	function calcTotalPrice() {
		let calcPrice = basePrice;
		let count = parseInt($('#count').val(), 10) || 1;
		
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
        let shotCount = $('#optionShot').val() || 0;
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
    productPrice($('input[name="productId"]').val());
	
});


/* 장바구니 담기 버튼 클릭 시 cartitem으로 form 전송 */
$('.addCartBtn').on("click", function(e) {
	e.preventDefault();
	

	const cartId = $('input[name="cartId"]').val();
 
	let optionSyrup = $("input[name='optionSyrup']:checked").map(function() {
		return $(this).val();
	}).get();
	if (optionSyrup.length === 0) {
		optionSyrup = [];
	}
	
	
	if (!cartId) {
        alert('로그인이 필요한 서비스입니다.');
        return;
    }

	// JSON 배열로 전달하기
	let cartItem = {
	    productId: $('input[name="productId"]').val(),
	    optionSize: parseInt($("input[name='optionSize']:checked").val()) || 0,
	    optionTemperature: parseInt($("input[name='optionTemperature']:checked").val()) || 0,
	    optionShot: parseInt($('#optionShot').val()) || 0,
	    optionSyrup: optionSyrup.map(Number),
	    count: parseInt($('#count').val()) || 1,
	    totalPrice: parseInt($('#total-price').text().replace(/,/g, '')) || 0
	}

	
	$.ajax({
		url: "/cart/insertCartItem",
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			cartId: cartId,
			cartItem: cartItem
		}),
		success: function(response) {
            alert('장바구니에 추가되었습니다!');
			location.href = "/";
        },
        error: function(xhr, status, error) {
			alert('장바구니에 추가하는 데 실패했습니다.');
			   console.error('AJAX 요청 실패:', status, error);
			   console.error('응답 텍스트:', xhr.responseText);
			   console.error('전송한 데이터:', JSON.stringify({
			       cartId: cartId,
			       cartItem: cartItem
			   }));
        }
	});
	console.log("cartId:", cartId);
	console.log("cartItem:", cartItem);
});



