$("articleInsertBtn").on("click", function(){
	if(!chkData("#name", "작성자명을")) return;
	else if(!chkData("#title", "제목을")) return;
	else if(!chkData("#content", "내용을")) return;
	else{
		actionProcess("#insertForm", "post", "/article/articleInsert");
	}
});

$("#articleUpdateBtn").on("click", function(){
	if(!chkData("#title", "제목을")) return;
	else if(!chkData("#content", "내용을")) return;
	else{
		actionProcess("#updateForm", "post", "/article/articleUpdate");
	}
});

$("articleCancelBtn").on("click", function(){
	let form = $(this).parents("form").attr("id");
	console.log(form);
	$("#"+form+"").each(function(){
		this.reset();
	})
});

$("#articleListBtn").on("click", function(){
	locationProcess("/article/articleList");
});

$("#insertFormBtn").on("click", function(){
	locationProcess("/article/insertForm");
});

$("#updateFormBtn").on("click", function(){
	let no = $("#no").val();
	actionProcess("#dataForm", "post", "/article/updateForm/"+no);
});

$("#articleDeleteBtn").click(function(){
	let no = $("#no").val();
	actionProcess("#dataForm", "delete", "/article/"+no);
});