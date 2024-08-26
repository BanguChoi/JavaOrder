$(document).ready(function() {
    $("#updateButton").click(function() {
        // 폼 데이터 가져오기
        var productData = {
            productId: $("#productId").val(),
            productName: $("#productName").val(),
            categoryCode: $("#categoryCode").text(),
            productPrice: parseFloat($("#productPrice").val()), // 가격을 float로 변환
            productExplain: $("#productExplain").val(),
            productSell: $("#productSell").val()
        };

        // AJAX 요청으로 데이터 전송
        $.ajax({
            type: "POST",
            url: "/products/" + productData.productId + "/update",
            contentType: "application/json",
            data: JSON.stringify(productData),
            success: function(response) {
                // 성공 시 목록 페이지로 리다이렉션
                alert("상품이 성공적으로 수정되었습니다.");
                window.location.href = "/products"; // 목록 페이지로 이동
            },
            error: function(xhr, status, error) {
                // 오류 처리
                alert("상품 수정에 실패하였습니다. 다시 시도해주세요.");
            }
        });
    });
});
