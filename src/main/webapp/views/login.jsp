<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/_header.jsp" />

<div class="login-page">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Sign in to continue</strong>
					</div>
					<div class="panel-body">
						<form action="/login" method="post"
							onsubmit="return confirmForChanges();">
							<fieldset>
								<div class="row">
									<div class="col-sm-12 col-md-10  col-md-offset-1 ">
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-user"></i>
												</span> <input class="form-control" placeholder="Username"
													name="username" type="text" id="username">
											</div>
											<div class="error" id="error-username"></div>
										</div>
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="glyphicon glyphicon-lock"></i>
												</span> <input class="form-control" placeholder="Password"
													name="password" type="password" id="password">
											</div>
											<div class="error" id="error-password"></div>
										</div>
										<div class="form-group">
											<input type="submit" class="btn btn-lg btn-primary btn-block"
												value="Sign in">
										</div>
									</div>
								</div>
							</fieldset>
						</form>
						<c:if test="${param.error ne null}">
							<div class="alert-danger">Invalid username and password.</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert-normal">You have been logged out.</div>
						</c:if>

					</div>

				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="layout/_footer.jsp" />