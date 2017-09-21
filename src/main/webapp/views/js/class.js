
// Format length of text
function formatLength() {
	$("#data-clazz td.text").each(function(index) {
		if ($(this).html().length > 25) {
			$(this).html($(this).html().substring(0, 25) + '...')
		}
	});
}

// Check vaild object clazz if success then send request to server
// If get error message then show it to form #modalInsertUpdate
function checkValidClass(clazz) {
	$('.error-message').each(function(index) {
		$(this).empty();
		$(this).addClass('alert-hide');
	});
	var flag = true;
	if (clazz.name.trim().length < 2 || clazz.name.trim().length > 255) {
		showMessage("name", "Name of class must be between 2 to 255");
		flag = false;
	}
	return flag;
}

// Bind error to view
function clearErrorMessage() {
	$("div.error").each(function(index) {
		$(this).html("");
	});
}
function changeTab() {
	if (window.location.pathname === "/class/index"
			|| window.location.pathname === "/class/") {
		$("#tab-update").removeClass("active");
		$("#tab-list").addClass("active");
		$(".tab-content #edit").removeClass("active");
		$(".tab-content #list").addClass("active");
	}
}


function searchClass() {
	var keyword = $("#search").val();
	keyword = validInputSearch(keyword);
	window.location.href = "/class/?page=1&keyword=" + keyword;
}

//Format length of text
function formatLengthJson(stringData){
	if(stringData.length > 15){
		return stringData.substring(0, 15) + '...';
	}
	return stringData;
}

function searchClassAjax(){
	$("#search-result").html("");
	var keyword = $("#search").val();
	keyword = validInputSearch(keyword);
	if(keyword === ""){
		$(".search-result.col-md-5.col-md-push-6").removeClass("show");
	}else{
		$.ajax({
			method : "GET",
			headers : {
				'Authorization' : token
			},
			url : "/api/class/getClassByKeyword?keyword="+keyword,
			success: function(result){
				var strResult = '';
				if(result.length > 0){
					$.each(result, function(index,value){
						strResult += "<tr>"
										+"<td class='text'>"+value.id+"</td>"
										+"<td class='text'>"+formatLengthJson(escapeXml(value.name))+"</td>"
									+"</tr>"
					});
				}else{
					strResult = "<p class='text-center'>Không tìm thấy dữ liệu</p>"
				}
				$("#search-result").html(strResult);
				$(".search-result.col-md-5.col-md-push-6").addClass("show");
			}
		});
	}
	
}

function getObject() {
	var clazz = {
		name : $("#name").val(),
	}
	return clazz;
}

function confirmForChanges() {
	clearErrorMessage();
	if (!checkValidClass(getObject())) {
		return false;
	}
	return true;

}

function viewMoreResult(event){
	event.preventDefault();
	searchClass();
}


$(document).ready(function() {
	changeTab();
	formatLength();
	$("button[data-action]").click(function() {
		action = $(this).attr("data-action");
		this.form.action = "class/" + action;
	});
});
