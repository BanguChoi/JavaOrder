$(document).ready(function(){
  // 수량조절 함수
	const updateCount = function(itemId, change) {
   		let $countInput = $(`#itemCount${itemId}`);
    	let currentCount = parseInt($countInput.val(), 10);
    	let newCount = Math.max(1, currentCount + change);
	
		$.ajax({
			url: '/cart/updateCartItem',
			method: 'POST',
			data: {
				itemId: itemId,
				itemNum: newCount
			},
			success: function(response){
				$countInput.val(newCount);
				calcTotalPrice();
			}, 
			error: function(xhr) {
		    	alert('수량 수정에 실패하였습니다.');
			}
		});
	};
	

  	$('.minusItemBtn').click(function(){
    	let itemId = $(this).data('item-id');
    	updateCount(itemId, -1);
    	calcTotalPrice();
  	});

  	$('.plusItemBtn').click(function(){
    	let itemId = $(this).data('item-id');
    	updateCount(itemId, 1);
    	calcTotalPrice();
  	});

  	function calcTotalPrice() {
    	let cartPrice = 0;

    	$('.cartItem').each(function() {
      	const itemPrice = parseInt($(this).find('.itemPrice').text(), 10);
      	const itemCount = parseInt($(this).find('.itemCount').val(), 10);
      	cartPrice += itemPrice * itemCount;
   	 });

    	$('#cartPrice').text(cartPrice);
		updateCart(cartPrice);

  	}
	
		
	function updateCart(cartPrice) {	
		$.ajax({
			url: '/cart/updateCart',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(cartPrice),
			success: function(response) {
			},
			error: function(xhr, status, error) {
			}
		});
	}


	
	$('.deleteItemBtn').click(function(){
	    let itemId = $(this).data('item-id');
	    let $item = $(this).closest('.cartItem');
	    
	    if(confirm('정말로 이 항목을 삭제하시겠습니까?')) {
	        $.ajax({
	            url: '/cart/deleteCartItem',
	            method: 'POST',
	            data: { itemId: itemId },
	            success: function(response) {
	                if(response.success) {
	                    $item.remove();
	                    calcTotalPrice();
	                } else {
	                    alert('항목 삭제에 실패했습니다.');
	                }
	            },
	            error: function(xhr, status, error) {
	                alert('삭제 중 오류가 발생했습니다.');
	            }
	        });
	    }
	});

  calcTotalPrice(); // 페이지 로드 시 초기 총 금액 계산
});