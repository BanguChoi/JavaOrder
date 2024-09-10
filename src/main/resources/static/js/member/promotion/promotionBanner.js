$(document).ready(function(){
    fetch('/admin/promotion/ongoing') // 진행 중인 프로모션을 불러오는 API
        .then(response => response.json())
        .then(data => {
			if (!Array.isArray(data)) {
				console.error('Received non-array data:', data);
				return;
			}
			
            const carouselItems = $('#carouselItems');
            let isActiveSet = false;

            data.forEach((promotion, index) => {
                const itemClass = isActiveSet ? 'carousel-item' : 'carousel-item active';
                isActiveSet = true; // 첫 번째 아이템만 active로 설정
                
                const promotionItem = `
                    <div class="${itemClass}">
                        <a href="/member/promotion/${promotion.promotionCode}">
                            <img src="/images/promotion/${promotion.promotionCode}.jpg" onerror="this.src='/images/noimg.png'" 
							class="d-block w-100" alt="${promotion.promotionTitle}" style="width:100%; height:350px; background-color:gray; object-fit: cover; object-position:top">
                        </a>
                    </div>
                `;
                carouselItems.append(promotionItem);
            });
        })
        .catch(error => {
            console.error('Error fetching ongoing promotions:', error);
        });
});
