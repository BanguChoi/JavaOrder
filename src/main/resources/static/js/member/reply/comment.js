//const template = (id, replyName, body) => {
const template = (data) => {
	let $div = $('#replyList');
	
	let $element = $('#item-template').clone().removeAttr('id');
	$element.attr("data-id", data.id);//확인 뒤 id에 replyId
	$element.addClass("reply");			//댓글 reply 생성
	$element.find('.replyName').append(data.replyName);
	$element.find('.regDate').append(getDateFormat(new Date(data.replyDate)));
	let replyContent = data.replyContent;
	$element.find('.card-text').html(replyContent); //csrd-text를 찾아서 repC를 넣느다.
	
	$div.append($element);	//속성 즉 위에 만들어둔 형식을 추가한다.
}//여기까지가 template를 구성한다.



const dataReset = () => { //Form 명만 맞으면 OK
	$('#commentForm').each(function(){
		this.reset();
	})
};

let replyId = $("#replyId").val();
console.log(replyId);
$.getJSON("/replys/all/"+replyId, function(result){
	
	for(let value of result){
		template(value);//위에 template가 완성되면 이 데이트럴 for문 넣기
	}
}).fail(function(){
	alert("댓글 목록을 불러오는데 실패하였습니다. 잠시 후에 다시 시도해 주세요.");
});


$(document).on("click", "#commentInsertBtn", function(){ //버튼명 바꾸기
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
	
	let card = $(this).parents("div.comment"); //이 comment는  reply의 일수있음
	$("#reply").val(card.data("reply"));				//이 "id"는 
	$("#replyName").val(card.find(".card-header .name").html()); //.name 찾아보고 replyContent
	$("#replyName").prop("readonly", true);
	
	let replyContent = card.find(".card-text").html();
	replyContent = ReplyContent.replace(/(<br>|<br\/>|<br \/>)/g, 'r/n/');
	$("#replyContent").val(replyContent);
	
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
				console.log(data.replyContent);
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
	let replyId = commentDeleteDataArea.data("replyId");
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
	let replyId = commentUpdateDataArea.data("replyId");
	console.log(replyId);
		
});