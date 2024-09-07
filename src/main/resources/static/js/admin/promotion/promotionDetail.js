/* 수정버튼 클릭 */
$("#updateFormBtn").on("click", function(){
	if(!chkData("#promotionTitle", "제목을")) return;
	else if(!chkData("#promotionStartDate", "시작일을")) return;
	else if(!chkData("#promotionEndDate", "종료일을")) return;
	else if ($("#promotionEndDate").val() < $("#promotionStartDate").val()) {
			alert("종료일은 시작일보다 빠를 수 없습니다.");
			return;
	}
	if($("input[type='file']").val()!=""){			// 업로드할 이미지 파일 존재 시
		if(!chkFile("input[type='file']")) return;	//이미지 파일만 업로드 가능 common에 선언
	}

	actionFileProcess("#dataForm", "POST", "/admin/promotion/promotionUpdate");
	
});


/* 삭제버튼 클릭 시 처리 이벤트*/
$("#promotionDeleteBtn").on("click", function(){
	if(confirm("정말 삭제하시겠습니까?")){
		actionProcess("#dataForm", "post", "/admin/promotion/promotionDelete");
	}
});


/* 등록 버튼 클릭 시 처리 버튼 */
$("#insertFormBtn").click(function(){
	locationProcess("/admin/promotion/insertForm");
});


/*  목록 버튼 클릭 시 처리 이벤트 */
$("#promotionListBtn").click(function(){
	locationProcess("/admin/promotion/promotionList");
});


/* 이미지 삽입 */
$('#manageImageButton').click(function() {
    $('#imageUpload').click();  // 파일 선택 창 열기
});

/* 이미지 삭제 */
$('#deleteImageButton').click(function() {
    var promotionCode = $("#promotionCode").val();

    $.ajax({
        url: '/admin/promotion/deleteImage',  // URL 수정
        type: 'POST',
        data: { promotionCode: promotionCode },
        success: function(response) {
            alert('이미지가 성공적으로 삭제되었습니다.');
            $('#promotionImage').attr('src', '/images/noimg.png?' + new Date().getTime()); // 캐시 무효화
        },
        error: function(xhr, status, error) {
            alert('이미지 삭제 중 오류가 발생했습니다.');
        }
    });
});

$('#imageUpload').change(function() {
    var formData = new FormData();
    var imageFile = $('#imageUpload')[0].files[0];
    var promotionCode = $("#promotionCode").val();

    formData.append('image', imageFile);
    formData.append('promotionCode', promotionCode);

    $.ajax({
        url: '/admin/promotion/uploadImage',  // URL 수정
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            alert('이미지가 성공적으로 업로드되었습니다.');
            $('#promotionImage').attr('src', '/images/' + promotionCode + '.' + imageFile.name.split('.').pop() + '?' + new Date().getTime());
			locationProcess("/admin/promotion/"+promotionCode);
        },
        error: function(xhr, status, error) {
			console.error("Error: ", error);
			console.error("Status: ", status);
			console.error("Response: ", xhr.responseText);
            alert('이미지 업로드 중 오류가 발생했습니다.');
        }
    });
});
/*
// 프로모션 상태 설정
$(".statusBtn").click(function(){
	let no = $("#promotionCode").val();
	let st = $(this).val();
	//$("#promotionStatus").val(st);	
	locationProcess(`/admin/promotion/updateOrders/${no}/${st}`);
})*/