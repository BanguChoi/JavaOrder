$(document).ready(function() {
    $('#manageImageButton').click(function() {
        $('#imageUpload').click();  // 파일 선택 창 열기
    });

    $('#deleteImageButton').click(function() {
        var productId = $('#productId').val();

        $.ajax({
            url: '/admin/products/deleteImage',  // URL 수정
            type: 'POST',
            data: { productId: productId },
            success: function(response) {
                alert('이미지가 성공적으로 삭제되었습니다.');
                $('#productImage').attr('src', '/images/noimg.png?' + new Date().getTime()); // 캐시 무효화
            },
            error: function(xhr, status, error) {
                alert('이미지 삭제 중 오류가 발생했습니다.');
            }
        });
    });

    $('#imageUpload').change(function() {
        var formData = new FormData();
        var imageFile = $('#imageUpload')[0].files[0];
        var productId = $('#productId').val();

        formData.append('image', imageFile);
        formData.append('productId', productId);

        $.ajax({
            url: '/admin/products/uploadImage',  // URL 수정
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                alert('이미지가 성공적으로 업로드되었습니다.');
                $('#productImage').attr('src', '/images/' + productId + '.' + imageFile.name.split('.').pop() + '?' + new Date().getTime());
            },
            error: function(xhr, status, error) {
                alert('이미지 업로드 중 오류가 발생했습니다.');
            }
        });
    });

    $('#updateButton').click(function() {
        var productId = $('#productId').val();
        var productName = $('#productName').val();
        var productPrice = parseFloat($('#productPrice').val());
        var productExplain = $('#productExplain').val();
        var productSell = $('#productSell').val();

        $.ajax({
            url: '/admin/products/' + productId,  // URL 수정
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                productName: productName,
                productPrice: productPrice,
                productExplain: productExplain,
                productSell: productSell
            }),
            success: function(response) {
                alert('상품 정보가 수정되었습니다.');
                window.location.href = '/admin/products';  // 경로 수정
            },
            error: function(xhr, status, error) {
                alert('상품 수정에 실패하였습니다.');
            }
        });
    });
});
