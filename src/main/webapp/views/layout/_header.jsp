<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="UTF-8">
	<title>Student</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<c:if test="${jwt!=null}">
		<meta name="jwt" content="${jwt}" />
	</c:if>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="/views/css/custom.css" rel="stylesheet" type="text/css" />
	<c:if test="${pageContext.request.requestURI == '/views/student.jsp'}">
		<link rel="stylesheet"
			href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="stylesheet"
			href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css" />
	</c:if>
</head>
<body>
	<div class="header">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<a class="navbar-brand" href="/student/">WebSiteName</a>
				<c:if test="${!(empty sessionScope['username'])}">
					<div class="navbar-header">
						<a class="navbar-brand" href="/student/">${username}</a>
					</div>
					<ul class="nav navbar-nav">
						<li class="active"><a href="/student/">Student</a></li>
						<c:if test="${role != 'ROLE_USER' }">
							<li><a href="/class/">Class</a></li>
						</c:if>
					</ul>
				</c:if>
				<c:if test="${!(empty sessionScope['role'])}">
					<form action="/logout" method="post">
						<input type="submit" class="btn btn-danger navbar-btn" value="Logout" /> 
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</c:if>
			</div>
		</nav>
	</div>