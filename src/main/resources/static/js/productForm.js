$(document).ready(function() {
    $('#submitBtn').on('click', function() {
        var formData = {
            productName: $('#productName').val(),
            categoryCode: $('#categoryCode').val(),
            productPrice: $('#productPrice').val(),
            productSell: $('#productSell').val(),
            productExplain: $('#productExplain').val()
        };

        $.ajax({
            type: "POST",
            url: "/products/new",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function(response) {
                alert("상품이 성공적으로 등록되었습니다.");
                window.location.href = "/products";
            },
            error: function(error) {
                alert("상품 등록 중 오류가 발생했습니다.");
                console.error("Error:", error);
            }
        });
    });
});
