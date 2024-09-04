// 페이징 처리 
$(".page-item a").on("click", function(e){
	e.preventDefault();
	/*$("#searchForm").find("input[name='pageNum']").val($(this).attr("href"));*/
	$("#page").val($(this).data("number"));
	actionProcess("#searchForm", "get", "/member/orderList/");
});

// 전체 데이터 조회
$("#allSearchBtn").on("click", function(){
	locationProcess("/member/orderList/");
});

$(document).ready(function(){
	$("#keyword").val("");
	$("#status").val("");
	$("#startDate").val("");
	$("#endDate").val("");
	
	$(".dateArea").hide();
	$(".selectArea").show();
	
	// 검색조건 변경 때마다 처리 이벤트
	$("#searchType").on("change", function(){
		if($("#searchType").val()=="orderStatus"){	// 주문상태 검색 시
			$(".dateArea").hide();
			$(".selectArea").show();
			$("#status").val("");
			$("#status").focus();
		}
		if($('#searchType').val()=="orderDate"){	// 날짜 선택 시
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
		if($("#searchType").val()=="orderStatus"){
			if(!chkData("#status", "주문상태를")) return;
		}
	}else if($("#searchType").val()=="orderDate"){
		$("#status").val("");
		if(!chkData("#startDate","시작날짜를")) return;
		else if(!chkData("#endDate","종료날짜를")) return;
		else if($('#startDate').val() > $('#endDate').val()){
			alert("시작날짜가 종료날짜보다 더 클 수 없습니다.");
			return;
		}
	}
	
	$("#page").val(1); // 페이지 초기화
	
	actionProcess("#searchForm", "get", "/member/orderList/");
});