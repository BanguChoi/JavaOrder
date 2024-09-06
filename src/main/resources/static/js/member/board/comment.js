//const template = (id, nickname, body) => {
const template = (data) => {
	let $div = $('#commentList');
	
	let $element = $('#item-template').clone().removeAttr('id');
	$element.attr("data-id", data.id);
	$element.addClass("comment");
	$element.find('.name').append(data.nickname);
	$element.find('.cdate').append(getDateFormat(new Date(data.cdate)));
	//$element.find('.card-text').html(data.body);
	let body = data.body;
	$element.find('.card-text').html(body);
	
	$div.append($element);
}

const dataReset = () => {
	$('#commentForm').each(function(){
		this.reset();
	})
};

let no = $("#no").val();
console.log(no);
$.getJSON("/comments/all/"+no, function(result){
	/*$(result).each(function(){
		let id = this.id;
		let nickname = this.nickname;
		let body = this.body;
		body = body.replace(/(\r\n|\r|\n)/g, "<br />");
		template(id, nickname, body);
	});*/
	for(let value of result){
		template(value);
	}
}).fail(function(){
	alert("댓글 목록을 불러오는데 실패하였습니다. 잠시 후에 다시 시도해 주세요.");
});


$(document).on("click", "#commentInsertBtn", function(){
	if(!checkForm("#nickname", "작성자를")) return;
	else if (!checkForm("#body", "내용을")) return;
	else{
		/* JSON.stringify() : JavaScript 값이나 객체를 JSON문자열로 변환 */
		$.ajax({
			url:"/comments/commentInsert", 
			method:"post",
			headers:{
				"Content-Type":"application/json"
			},
			data: JSON.stringify({
				nickname:$("#nickname").val(),
				body:$("#body").val(),
				article:{
					no:no
				}
			}),
			//dataType:"text"
			dataType:"json"
//		}).done(function(result){
		}).done(function(data){
			//if(result!=""){
			if(data!=""){
				alert("댓글 등록이 완료되었습니다.");
				//let data = JSON.parse(result);
				dataReset();
				//template(data.id, data.nickname, data.body);
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
	$("#nickname").val(card.find(".card-header .name").html());
	$("#nickname").prop("readonly", true);
	
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
	$("#nickname").prop("readonly", false);
	$("#commentForm button[type='button']").attr("id", "commentInsertBtn");
	$("#commentForm button[type='button'].resetBtn").detach();
	$("#id").val(0);
});

$(document).on("click", "#commentUpdateBtn", function(){
	let commentId = $("#id").val();
	if(!checkForm("#body", "댓글내용을")) return;
	else{
		$.ajax({
			url:'/comments/'+commentId,
			method:'put',
			headers:{
				"Content-Type": "application/json",
			},
			data:JSON.stringify({
				body:$("#body").val()
			}),
			dataType:'json'
		}).done(function(data){
			if(data != ""){
				alert("댓글 수정이 완료되었습니다.");
				dataReset();
				console.log(data.body);
				$("#commentList").find("div[data-id="+commentId+"]").find(".card-text").html(data.body.replace(/(\r\n|\r|\n)/g, "<br />"));
			}
		}).fail(function(){
			alert("시스템에 문제가 있어 잠시 후에 다시 진행해 주세요.");
		})
		
	}
})

$(document).on("click", ".commentDeleteBtn", function(e){
	e.preventDefault();
	let commentDeleteDataArea = $(this).parents("div.comment");
	let commentId = commentDeleteDataArea.data("id");
	console.log(commentId);
	
	if(confirm("선택하신 댓글을 삭제하시겠습니까?")){
		$.ajax({
			url:'/comments/'+commentId,
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
	let commentId = commentUpdateDataArea.data("id");
	console.log(commentId);
		
});