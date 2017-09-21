var patternDate = /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;
var patternNumber = /^[0-9]*$/;

var keyword = $("#search").val();
var token = $("meta[name='jwt']").attr("content");
var accessDate = new Date();
accessDate.setDate(accessDate.getDate() - 365);

var XML_CHAR_MAP = {
	'<' : '&lt;',
	'>' : '&gt;',
	'&' : '&amp;',
	'"' : '&quot;',
	"'" : '&apos;'
};

function escapeXml(s) {
	return s.replace(/[<>&"']/g, function(ch) {
		return XML_CHAR_MAP[ch];
	});
}

function checkLengthFloat(bigDecimal) {
	var arrNum = bigDecimal.split(".");
	var length = arrNum.length;
	if (length > 1 && arrNum[1].length > 10) {
		return false;
	}
	return true
}

function showMessage(field, message) {
	$("#error-" + field).append("<p>" + message + "</p>");
}


function getQueryParam(strPathQuery) {
	var arrParam = strPathQuery.split("&");
	var result = [];
	console.log(arrParam);
	$.each(arrParam, function(index, value) {
		arrayObject = value.split("=");
		result.push({
			"key" : arrayObject[0],
			"value" : arrayObject[1]
		});
	});
	console.log(result);
	return result;
}

function validInputSearch(strData) {
	var result = strData.replace(/`/g, "");
	return result;
}

// Format date with pattern dd/mm/yyyy
function formatDate(stringDate) {
	var date = new Date(stringDate);
	return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear();
}

function updateCss() {
	$("#dateOfBirth").datepicker({
		autoSize : true,
		dateFormat : 'dd/mm/yy',
		changeMonth : true,
		changeYear : true,
		maxDate : "-1Y",
		yearRange : "-50:+0"
	});
}

function changeActiveNav() {
	console.log(window.location.pathname.indexOf("student") > 0);
	$(".nav.navbar-nav li").each(function(index){
		$(this).removeClass("active");
	});
	if (window.location.pathname.indexOf("student") > 0) {
		$(".nav.navbar-nav li:nth-child(1)").addClass("active")
	}else if(window.location.pathname.indexOf("class") > 0){
		$(".nav.navbar-nav li:nth-child(2)").addClass("active")
	}
}
$(document).mouseup(function(e) {
	var container = $(".search-result.col-md-5.col-md-push-6");
	if (!container.is(e.target) && container.has(e.target).length === 0) {
		container.removeClass("show");
	}
});
$(document).ready(function() {
	updateCss();
	changeActiveNav();
});