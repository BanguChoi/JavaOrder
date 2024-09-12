/*
	const template = (data) => {
	    let $div = $('#replyList');	    
	    let $element = $('#item-template').clone().removeAttr('id');
	    $element.attr("data-id", data.replyId); // replyId를 사용
	    $element.addClass("reply"); // 댓글(reply) 생성
	    $element.find('.replyName').append(data.replyName);
	    $element.find('.regDate').append(getDateFormat(new Date(data.replyDate)));	
	    let replyContent = data.replyContent;
	    $element.find('.card-text').html(replyContent); // card-text를 찾아서 replyContent를 넣는다.
	    
	    $div.append($element); // 속성 즉 위에 만들어둔 형식을 추가한다.
	}
*/	
	const template = (data) => {
	    let $div = $('#replyList');	    
	    // 템플릿 요소를 클론합니다.
	    let $element = $('#item-template').clone().removeAttr('id');	    
	    // data-id 속성 설정
	    $element.attr("data-id", data.replyId);	    
	    // 'reply' 클래스 추가
	    $element.addClass("reply");	    
	    $element.removeAttr('style');	    
	    $element.find('.replyName').text(data.replyName); // 텍스트를 추가
	    $element.find('.regDate').text(getDateFormat(new Date(data.replyDate))); // 날짜 형식 추가
		$element.find('.card-text').addClass("text-start").html(data.replyContent); // 댓글 내용 추가


	    // #replyList에 복사한 요소를 추가합니다.
	    $div.append($element);
	}

const dataReset = () => { // Form 명만 맞으면 OK
    $('#replyForm').each(function() {
        this.reset();
    })
};

//let replyId = $("#replyId").val();
//console.log(replyId);
//$.getJSON("/replys/all/" + replyId, function(result) {
$.getJSON("/replys/all/" + $("#boardNo").val(), function(result) {
    for (let value of result) {
        template(value); // 위에 template가 완성되면 이 데이털 for문 넣기
    }
}).fail(function() {
    alert("댓글 목록을 불러오는데 실패하였습니다. 잠시 후에 다시 시도해 주세요.");
});

$(document).on("click", "#replyInsertBtn", function() { // 버튼명 바꾸기
    if (!checkForm("#replyName", "작성자를")) return;
    else if (!checkForm("#replyContent", "내용을")) return;
    else {
        /* JSON.stringify() : JavaScript 값이나 객체를 JSON문자열로 변환 */
        $.ajax({
            url: "/replys/replyInsert",
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            data: JSON.stringify({
                replyName: $("#replyName").val(),
                replyContent: $("#replyContent").val(),
                board: {
                    boardNo: $("#boardNo").val()
                }
            }),
            dataType: "json"
        }).done(function(data) {
            if (data != "") {
                alert("댓글 등록이 완료되었습니다.");
                dataReset();
                template(data);
            }
        }).fail(function() {
            alert("시스템에 문제가 있어 잠시 후에 다시 진행해 주세요.");
        });
    }
});

$(document).on("click", ".replyUpdateFormBtn", function(e) {
    e.preventDefault();
	
    $("#replyForm .resetBtn").detach();
    
    let card = $(this).parents("div.reply"); // 'reply'로 변경
    $("#replyId").val(card.data("id")); // 'id'는 replyId로 변경 123
    $("#replyName").val(card.find(".card-header .replyName").html()); // 'replyName'으로 변경
    $("#replyName").prop("readonly", true);
    
    let replyContent = card.find(".card-text").html();
    replyContent = replyContent.replace(/(<br>|<br\/>|<br \/>)/g, 'r/n/');
    $("#replyContent").val(replyContent);
    
    $("#replyForm button[type='button']").attr("id", "replyUpdateBtn");
    let resetButton = $("<button type='button' class='btn btn-secondary col-sm-1 resetBtn mx-2'>");
    resetButton.html("취소");
    $("#replyForm .sendBtn").after(resetButton);
}); 

$(document).on("click", ".resetBtn", function() {
    dataReset();
    $("#replyName").prop("readonly", false);
    $("#replyForm button[type='button']").attr("id", "replyInsertBtn");
    $("#replyForm button[type='button'].resetBtn").detach();
    $("#replyId").val(0);
});

$(document).on("click", "#replyUpdateBtn", function() {
    let replyId = $("#replyId").val();
	console.log("replyId before AJAX:", replyId);
    if (!checkForm("#replyContent", "댓글내용을")) return;
    else {
        $.ajax({
            url: '/replys/' + replyId,
            method: 'put',
            headers: {
                "Content-Type": "application/json",
            },
            data: JSON.stringify({
                replyContent: $("#replyContent").val()
            }),
            dataType: 'json'
        }).done(function(data) {
            if (data != "") {
                alert("댓글 수정이 완료되었습니다.");
                dataReset();
                console.log(data.replyContent);
                $("#replyList").find("div[data-id=" + replyId + "]").find(".card-text").html(data.replyContent.replace(/(\r\n|\r|\n)/g, "<br />"));
            }
        }).fail(function() {
            alert("시스템에 문제가 있어 잠시 후에 다시 진행해 주세요.");
        })
    }
})

$(document).on("click", ".replyDeleteBtn", function(e) {
    e.preventDefault();
    let replyDeleteDataArea = $(this).parents("div.reply"); // 'reply'로 변경
    let replyId = replyDeleteDataArea.data("id"); // 'id'는 replyId로 변경
    console.log(replyId);
    
    if (confirm("선택하신 댓글을 삭제하시겠습니까?")) {
        $.ajax({
            url: '/replys/' + replyId,
            type: 'delete',
            dataType: 'text'
        }).done(function() {
            alert("댓글 삭제가 완료되었습니다.");
            replyDeleteDataArea.remove();
        }).fail(function() {
            alert("시스템에 문제가 있어 잠시 후에 다시 진행해 주세요.");
        });
    }
});

$(document).on("click", ".replyUpdateBtn", function() {
    let replyUpdateDataArea = $(this).parents("div.reply"); // 'reply'로 변경
    let replyId = replyUpdateDataArea.data("id"); // 'id'는 replyId로 변경
    console.log(replyId);
});