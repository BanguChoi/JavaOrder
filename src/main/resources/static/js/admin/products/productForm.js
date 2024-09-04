$(document).ready(function() {
    // "카테고리 추가" 버튼 클릭 시 새 카테고리 입력 폼을 표시
    $('#showAddCategoryForm').click(function() {
        $('#newCategoryForm').toggle();  // 토글 방식으로 입력 폼을 표시/숨김
    });

    // 새 카테고리 추가 버튼 클릭 시
    $('#addNewCategoryBtn').click(function() {
        var newCategoryPrefix = $('#newCategoryPrefix').val().trim();
        var newCategoryName = $('#newCategoryName').val().trim();

        if (!newCategoryPrefix || !newCategoryName) {
            alert("카테고리 코드와 이름을 입력하세요.");
            return;
        }

        $.ajax({
            url: '/categories/create',
            type: 'POST',
            contentType: 'application/json',  // 데이터를 JSON 형태로 전송
            data: JSON.stringify({
                prefix: newCategoryPrefix,
                name: newCategoryName
            }),
            success: function(newCategory) {
                alert('새 카테고리가 추가되었습니다.');
                
                // 새로 추가된 카테고리를 select 옵션에 추가
                $('#categoryCode').append(new Option(newCategory.name, newCategory.code));
                
                // 새 카테고리를 기본 선택으로 설정
                $('#categoryCode').val(newCategory.code);
                
                // 입력 폼 초기화 및 숨기기
                $('#newCategoryPrefix').val('');
                $('#newCategoryName').val('');
                $('#newCategoryForm').hide();
            },
            error: function(xhr, status, error) {
                console.error("카테고리 추가 중 오류 발생:", status, error);
                alert('카테고리 추가 중 오류가 발생했습니다.');
            }
        });
    });

    // 상품 등록 버튼 클릭 시
    $('#submitBtn').click(function() {
        var productData = {
            productName: $('#productName').val().trim(),
            category: {
                code: $('#categoryCode').val()
            },
            productPrice: $('#productPrice').val().trim(),
            productSell: $('#productSell').val(),
            productExplain: $('#productExplain').val().trim()
        };

        if (!productData.productName || !productData.category.code || !productData.productPrice) {
            alert("상품명, 카테고리 코드, 가격을 모두 입력하세요.");
            return;
        }

        $.ajax({
            url: '/products',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(productData),
            success: function(response) {
                alert('상품이 성공적으로 등록되었습니다.');
                window.location.href = '/products'; // 상품 등록 후 목록으로 이동
            },
            error: function(xhr, status, error) {
                console.error("상품 등록 중 오류 발생:", status, error);
                alert('상품 등록 중 오류가 발생했습니다.');
            }
        });
    });
});
