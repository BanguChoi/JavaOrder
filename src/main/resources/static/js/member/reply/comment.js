//const template = (id, replyName, body) => {
const template = (data) => {
	let $div = $('#replyList');
	
	let $element = $('#item-template').clone().removeAttr('id');
	$element.attr("data-id", data.id);
	$element.addClass("reply");
	$element.find('.replyName').append(data.replyName);
	$element.find('.regDdate').append(getDateFormat(new Date(data.replyDate)));
	let reply = data.replyContent;
	$element.find('.card-text').html(replyContent);
	
	$div.append($element);
}

const dataReset = () => {
	$('#commentForm').each(function(){
		this.reset();
	})
};

let replyId = $("#replyId").val();
console.log(replyId);
$.getJSON("/replys/all/"+replyId, function(result){
	
	for(let value of result){
		template(value);
	}
}).fail(function(){
	alert("댓글 목록을 불러오는데 실패하였습니다. 잠시 후에 다시 시도해 주세요.");
});


$(document).on("click", "#commentInsertBtn", function(){
	if(!checkForm("#replyName", "작성자를")) return;
	else if (!checkForm("#replyContent", "내용을")) return;
	else{
		/* JSON.stringify() : JavaScript 값이나 객체를 JSON문자열로 변환 */
		$.ajax({
			url:"/replys/replyInsert", 
			method:"post",
			headers:{
				"Content-Type":"application/json"
			},
			data: JSON.stringify({
				replyName:$("#replyName").val(),
				replyContent:$("#replyContent").val(),
				reply:{
					replyId:replyId
				}
			}),
			dataType:"json"
		}).done(function(data){
			//if(result!=""){
			if(data!=""){
				alert("댓글 등록이 완료되었습니다.");
				dataReset();
				template(data);
			}
		}).fail(function(){
			alert("시스템에 문제가 있어 잠시 후에 다시 진행해 주세요.");
		});
	}
});

$(document).on("click", ".commentUpdateFormBtn", function(e){
	e.preventDefault();
	$("#commentForm .resetBtn").detach();
	
	let card = $(this).parents("div.comment");
	$("#id").val(card.data("id"));
	$("#replyName").val(card.find(".card-header .name").html());
	$("#replyName").prop("readonly", true);
	
	let body = card.find(".card-text").html();
	body = body.replace(/(<br>|<br\/>|<br \/>)/g, 'r/n/');
	$("#body").val(body);
	
	$("#commentForm button[type='button']").attr("id", "commentUpdateBtn");
	let resetButton = $("<button type='button' class='btn btn-secondary col-sm-1 resetBtn mx-2'>");
	resetButton.html("취소");
	$("#commentForm .sendBtn").after(resetButton);
}); 	

$(document).on("click", ".resetBtn", function(){
	dataReset();
	$("#replyName").prop("readonly", false);
	$("#commentForm button[type='button']").attr("id", "commentInsertBtn");
	$("#commentForm button[type='button'].resetBtn").detach();
	$("#replyId").val(0);
});

$(document).on("click", "#commentUpdateBtn", function(){
	let replyId = $("#replyId").val();
	if(!checkForm("#replyContent", "댓글내용을")) return;
	else{
		$.ajax({
			url:'/replys/'+replyId,
			method:'put',
			headers:{
				"Content-Type": "application/json",
			},
			data:JSON.stringify({
				replyContent:$("#replyContent").val()
			}),
			dataType:'json'
		}).done(function(data){
			if(data != ""){
				alert("댓글 수정이 완료되었습니다.");
				dataReset();
				console.log(data.body);
				$("#replyList").find("div[data-id="+replyId+"]").find(".card-text").html(data.replyContent.replace(/(\r\n|\r|\n)/g, "<br />"));
			}
		}).fail(function(){
			alert("시스템에 문제가 있어 잠시 후에 다시 진행해 주세요.");
		})
		
	}
})

$(document).on("click", ".commentDeleteBtn", function(e){
	e.preventDefault();
	let commentDeleteDataArea = $(this).parents("div.comment");
	let replyId = commentDeleteDataArea.data("id");
	console.log(replyId);
	
	if(confirm("선택하신 댓글을 삭제하시겠습니까?")){
		$.ajax({
			url:'/replys/'+replyId,
			type: 'delete',
			dataType:'text'
		}).done(function(){
			alert("댓글 삭제가 완료되었습니다.");
			commentDeleteDataArea.remove();
		}).fail(function(){
			alert("시스템에 문제가 있어 잠시 후에 다시 진행해 주세요.");
		});
	}
});


$(document).on("click", ".commentUpdateBtn", function(){
	let commentUpdateDataArea = $(this).parents("div.comment");
	let replyId = commentUpdateDataArea.data("id");
	console.log(replyId);
		
});