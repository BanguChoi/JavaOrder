$(document).ready(function() {
    $('#showAddCategoryForm').click(function() {
        $('#newCategoryForm').toggle();
    });

    $('#addNewCategoryBtn').click(function() {
        var newCategoryPrefix = $('#newCategoryPrefix').val().trim();
        var newCategoryName = $('#newCategoryName').val().trim();

        if (!newCategoryPrefix || !newCategoryName) {
            alert("카테고리 코드와 이름을 입력하세요.");
            return;
        }

        $.ajax({
            url: '/admin/categories/create',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                prefix: newCategoryPrefix,
                name: newCategoryName
            }),
            success: function(newCategory) {
                alert('새 카테고리가 추가되었습니다.');
                $('#categoryCode').append(new Option(newCategory.name, newCategory.code));
                $('#categoryCode').val(newCategory.code);
                $('#newCategoryPrefix').val('');
                $('#newCategoryName').val('');
                $('#newCategoryForm').hide();
            },
            error: function(xhr, status, error) {
                alert('카테고리 추가 중 오류가 발생했습니다.');
            }
        });
    });

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
            url: '/admin/products',  // URL 수정
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(productData),
            success: function(response) {
                alert('상품이 성공적으로 등록되었습니다.');
                window.location.href = '/admin/products';  // 경로 수정
            },
            error: function(xhr, status, error) {
                alert('상품 등록 중 오류가 발생했습니다.');
            }
        });
    });
});
