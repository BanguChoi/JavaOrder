// 상태 설정
$(".statusBtn").click(function(){
	let no = $(this).parents("tr").data("no");
	let st = $(this).val();
			
	locationProcess(`/admin/updateOrders/${no}/${st}`);
})

// 페이징 처리 
$(".page-item a").on("click", function(e){
	e.preventDefault();
	/*$("#searchForm").find("input[name='pageNum']").val($(this).attr("href"));*/
	$("#page").val($(this).data("number"));
	actionProcess("#searchForm", "get", "/admin/orderList");
});

// 전체 데이터 조회
$("#allSearchBtn").on("click", function(){
	locationProcess("/admin/orderList");
});

$(document).ready(function(){
	$("#keyword").val("");
	$("#status").val("");
	$("#startDate").val("");
	$("#endDate").val("");
	
	$(".selectArea").hide();
	$(".dateArea").hide();
	$(".textArea").show();
	
	// 검색조건 변경 때마다 처리 이벤트
	$("#searchType").on("change", function(){
		if($("#searchType").val()=="memberCode"){	// 회원번호 검색 시
			$(".selectArea").hide();
			$(".dateArea").hide();
			$(".textArea").show();
			$("#keyword").val("");
			$("#keyword").focus();
		}
		if($("#searchType").val()=="orderStatus"){	// 주문상태 검색 시
			$(".textArea").hide();
			$(".dateArea").hide();
			$(".selectArea").show();
			$("#keyword").val("");
		}
		if($('#searchType').val()=="orderDate"){	// 날짜 선택 시
			$(".textArea").hide();
			$(".selectArea").hide();
			$(".dateArea").show();
		}
	});
});

// 검색 버튼 클릭 시 처리 이벤트 
$("#searchBtn").on("click", function(){
	if($("#searchType").val()!="orderDate"){
		$("#startDate").val("");
		$("#endDate").val("");
		if($("#searchType").val()=="memberCode"){
			$("#status").val("");
			if(!chkData("#keyword", "검색어를")) return;
		}else if($("#searchType").val()=="orderStatus"){
			$("#keyword").val("");
			if(!chkData("#status", "주문상태를")) return;
		}
	}else if($("#searchType").val()=="orderDate"){
		$("#keyword").val("");
		$("#status").val("");
		if(!chkData("#startDate","시작날짜를")) return;
		else if(!chkData("#endDate","종료날짜를")) return;
		else if($('#startDate').val() > $('#endDate').val()){
			alert("시작날짜가 종료날짜보다 더 클 수 없습니다.");
			return;
		}
	}
	
	$("#page").val(1); // 페이지 초기화
	
	actionProcess("#searchForm", "get", "/admin/orderList");
});