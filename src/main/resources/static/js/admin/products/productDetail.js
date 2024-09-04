$(document).ready(function() {
    // 이미지 관리 버튼 클릭 시 파일 선택창 열기
    $('#manageImageButton').click(function() {
        $('#imageUpload').click();  // 파일 선택 창 열기
    });

    // 이미지 삭제 버튼 클릭 시
    $('#deleteImageButton').click(function() {
        var productId = $('#productId').val();

        $.ajax({
            url: '/products/deleteImage',
            type: 'POST',
            data: { productId: productId },
            success: function(response) {
                alert('이미지가 성공적으로 삭제되었습니다.');
                $('#productImage').attr('src', '/images/noimg.png?' + new Date().getTime()); // 캐시 무효화
            },
            error: function(xhr, status, error) {
                alert('이미지 삭제 중 오류가 발생했습니다.');
                console.error(status, error);
            }
        });
    });

    // 파일 선택 시 이미지 업로드 처리
    $('#imageUpload').change(function() {
        var formData = new FormData();
        var imageFile = $('#imageUpload')[0].files[0];
        var productId = $('#productId').val();

        formData.append('image', imageFile);
        formData.append('productId', productId);

        $.ajax({
            url: '/products/uploadImage',
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
                console.error(status, error);
            }
        });
    });

    // 수정 버튼 클릭 시 실행할 스크립트
    $('#updateButton').click(function() {
        var productId = $('#productId').val();
        var productName = $('#productName').val();
        var productPrice = parseFloat($('#productPrice').val()); // 가격을 float로 변환
        var productExplain = $('#productExplain').val();
        var productSell = $('#productSell').val();

        // AJAX 요청으로 데이터 전송
        $.ajax({
            url: '/products/' + productId,
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
                window.location.href = '/products'; // 수정 후 목록 페이지로 이동
            },
            error: function(xhr, status, error) {
                alert('상품 수정에 실패하였습니다. 다시 시도해주세요.');
            }
        });
    });
});
