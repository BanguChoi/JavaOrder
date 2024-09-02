
/*
	함수명 : chkData(유효성 체크 대상, 메세지 내용)
	출력 영역 : alert
	예시 : if(!chkData("#keyword"), "검색어")) return;
*/

function chkData(item, msg) {
	if($(item).val().replace(/\s/g, "") == "") {
		alert(msg + " 입력해 주세요.");
		$(item).val("");
		$(item).focus();
		return false;
	} else {
		return true;
	}
}

/*
	dataCheck(유효성 체크 대상, 출력 영역, 메세지 내용)
	출력 영역 : 필드
*/
function dataCheck(item, out, msg) {
	if($(item).val().replace(/\s/g, "") == "") {
		$(out).html(msg + " 입력해 주세요.");
		$(item).val("");
		return false;	
	} else {
		return true;
	}
}

/*
	함수명: checkForm(유효성 체크 대상, 메세지 내용)
	출력영역: placeholder 속성을 이용
	예시: if(!checkForm("#keyword", "검색어를")) return;
*/
function checkForm(item, msg) {
	var message = "";
	if($(item).val().replace(/\s/g, "") == "") {
		message = msg + " 입력해 주세요";
		$(item).attr("placeholder", message);
		return false;
	} else {
		return true;
	}
}


const actionProcess = function(form, method, action) {
	$(form).attr({
		"method": method,
		"action": action
	});
	$(form).submit();
}

const locationProcess = function(url) {
	location.href = url;
}

function getDateFormat(dateValue) {
	var year = dateValue.getFullYear();
	
	var month = dateValue.getMonth() + 1;
	month = (month < 10) ? "0" + month : month;
	
	var day = dateValue.getDate();
	day = (day < 10) ? "0" + day : day;
	
	var result = year + "-" + month + "-" + day;
	
	return result;
}

// 수량조절 함수
const updateCount = function(itemSelector, change) {
    let $countInput = $(itemSelector);
    let count = parseInt($countInput.val(), 10);

    // 수량을 변경, 최소 수량 1로 제한
    let newCount = Math.max(1, count + change);
    $countInput.val(newCount);
};






