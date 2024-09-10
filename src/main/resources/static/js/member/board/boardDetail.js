
/* 수정버튼 클릭 */
$("#updateFormBtn").on("click", function(){
	actionProcess("#dataForm", "post", "/member/board/updateForm");
});


// 비밀번호 삭제...
$(document).ready(function() {
    $("#boardDeleteBtn").on("click", function(event) {
        event.preventDefault(); // 기본 폼 제출 동작을 방지

        if (!chkData("#boardPasswd", "비밀번호를")) return;
        // 폼 데이터 수집
        var formData = $("#updateForm").serialize();

        $.ajax({
            url: "/memberBoard/pwdConfirm",
            method: "POST",
            dataType:"text",
			data: {
				"boardNo" : $("#boardNo").val(),
				"passwd" : $("#boardPasswd").val()
			}, 
            success: function(response) {
				if(response=="일치"){
					if(confirm("정말 삭제하시겠습니까?")){							
						actionProcess("#dataForm", "post", "/member/board/boardDelete");
					}
				}
				else if(response=="불일치"){
					alert("비밀번호가 일치하지 않습니다");
					return;
				}
            },
            error: function(xhr) {
                // 오류가 발생한 경우, 오류 메시지를 표시
               
			}
        });
    });

});

/* 등록 버튼 클릭 시 처리 버튼 */
$("#insertFormBtn").click(function(){
	locationProcess("/member/board/insertForm");
});

/*  목록 버튼 클릭 시 처리 이벤트 */
$("#boardListBtn").click(function(){
	locationProcess("/member/board/boardList");
});