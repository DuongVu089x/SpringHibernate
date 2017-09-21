
// Format length of text
function formatLength() {
	$("#data-student td.text").each(function(index) {
		if ($(this).html().length > 25) {
			$(this).html($(this).html().substring(0, 25) + '...')
		}
	});
	
}

function changeFortmatDate() {
	$("#data-student td.text.date").each(function(index){
		strDate = $(this).html();
		arrData = strDate.split("-");
		$(this).html(arrData[2] + "/" + arrData[1] + "/" + arrData[0]);
	});
}

// Check vaild object student if success then send request to server
// If get error message then show it to form #modalInsertUpdate
function checkValidStudent(student) {
	$('.error-message').each(function(index) {
		$(this).empty();
		$(this).addClass('alert-hide');
	});
	var flag = true;
	if (student.name.trim().length < 2 || student.name.trim().length > 255) {
		showMessage("name", "Name of student must be between 2 to 255");
		flag = false;
	}
	if (student.code.trim().length < 2 || student.code.trim().length > 11
			|| !(patternNumber.test(student.code))) {
		showMessage("code",
				"Code of student must be between 2 and 11 and only should containt number");
		flag = false;
	}
	if (student.address.trim().length < 2 || student.address.trim().length > 50) {
		showMessage("address", "Address of student must be between 2 and 50");
		flag = false;
	}
	if (student.averageScore === "" || student.averageScore < 0.0
			|| student.averageScore > 10.0
			|| !(checkLengthFloat(student.averageScore))) {
		showMessage(
				"averageScore",
				"Average Score of student must be between 0.0 and 10.0 and the part after '.' must be between 0 and 10");
		flag = false;
	}
	var dateParts = student.dateOfBirth.split("/");
	dateChoose = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
	if (!patternDate.test(student.dateOfBirth)
			|| dateChoose.getTime() >= accessDate.getTime()) {
		showMessage(
				"dateOfBirth",
				"Please enter date with pattern = dd/mm/yyyy and day must be smaller than present one year");
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
	if (window.location.pathname === "/student/index"
			|| window.location.pathname === "/student/") {
		$("#tab-update").removeClass("active");
		$("#tab-list").addClass("active");
		$(".tab-content #edit").removeClass("active");
		$(".tab-content #list").addClass("active");
	}
}


function searchStudent() {
	var keyword = $("#search").val();
	keyword = validInputSearch(keyword);
	window.location.href = "/student/?page=1&keyword=" + keyword;
}

//Format length of text
function formatLengthJson(stringData){
	if(stringData.length > 15){
		return stringData.substring(0, 15) + '...';
	}
	return stringData;
}

function searchStudentAjax(){
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
			url : "/api/student/getStudentByKeyword?keyword="+keyword,
			success: function(result){
				var strResult = '';
				if(result.length > 0){
					$.each(result, function(index,value){
						strResult += "<tr>"
										+"<td class='text'>"+formatLengthJson(escapeXml(value.name))+"</td>"
										+"<td class='text'>"+formatLengthJson(escapeXml(value.code))+"</td>"
										+"<td class='text'>"+formatLengthJson(escapeXml(value.address))+"</td>"
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
	var student = {
		name : $("#name").val(),
		createName : $("#createName").val(),
		updateName : $("#updateName").val(),
		code : $("#code").val(),
		address : $("#address").val(),
		averageScore : $("#averageScore").val(),
		dateOfBirth : $("#dateOfBirth").datepicker({
			dateFormat : "dd/MM/yy"
		}).val()
	}
	return student;
}

function confirmForChanges() {
	clearErrorMessage();
	if (!checkValidStudent(getObject())) {
		return false;
	}
	return true;

}

function viewMoreResult(event){
	event.preventDefault();
	searchStudent();
}


$(document).ready(function() {
	changeTab();
	formatLength();
	changeFortmatDate();
	$("button[data-action]").click(function() {
		action = $(this).attr("data-action");
		this.form.action = "student/" + action;
	});
});
