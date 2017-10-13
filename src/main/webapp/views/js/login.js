function clearErrorMessage() {
	$("div.error").each(function(index) {
		$(this).html("");
	});
}

function confirmForChanges() {
	clearErrorMessage();
	if (!checkValidFormLogin(getObject())) {
		return false;
	}
	return true;

}

function checkValidFormLogin(object) {
	$('.error-message').each(function(index) {
		$(this).empty();
		$(this).addClass('alert-hide');
	});
	var flag = true;
	if (object.username.trim().length < 2 || object.username.trim().length > 255) {
		showMessage("username", "Uername must be between 2 to 255");
		flag = false;
	}
	if (object.password.trim().length < 2 || object.password.trim().length > 255) {
		showMessage("password", "Password must be between 2 to 255");
		flag = false;
	}
	return flag;
}

function getObject() {
	var object = {
		username : $("#username").val(),
		password : $("#password").val()
	}
	return object;
}
