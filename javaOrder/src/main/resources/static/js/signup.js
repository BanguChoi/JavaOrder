function openAddressSearch() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색된 주소를 입력 필드에 설정
                document.getElementById('address').value = data.roadAddress;
            }
        }).open();
    }