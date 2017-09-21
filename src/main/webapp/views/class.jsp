<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="layout/_header.jsp" />

<div class="class-content">
	<div class="container">
		<h2 class="text-center">QUẢN LÝ LỚP HỌC</h2>
		<div class="row">
			<div class="input-group col-md-6 col-md-push-6">
				<input type="text" id="search" class="form-control"	placeholder="Name" value="${keyword}"
					onkeyup="searchClassAjax()" /> <span class="input-group-btn">
					<a class="btn btn-info" onclick="searchStudent()"> <i
						class="glyphicon glyphicon-search"></i>
				</a>
				</span>
			</div>
			<div class="search-result col-md-5 col-md-push-6">
				<table id="data-class" class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
						</tr>
					</thead>
					<tbody id="search-result">
					</tbody>
				</table>
				<p class="text-center">
					<a href="#" onclick="viewMoreResult(event)">Xem thêm</a>
				</p>
			</div>
		</div>
		<!-- Nav tabs -->
		<div class="row">

			<ul class="nav nav-tabs" role="tablist">
				<li class="active" id="tab-update"><a href="#edit" role="tab"
					data-toggle="tab">CẬP NHẬT</a></li>
				<li id="tab-list"><a href="#list" role="tab" data-toggle="tab">DANH
						SÁCH</a></li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<div class="tab-pane active" id="edit">
					${message}
					<form:form action="class/index" commandName="clazz" role="form"
						onsubmit="return confirmForChanges();">
						<form:hidden path="id" cssClass="form-control" />
						<div class="form-group">
							<label for="name">Tên</label>
							<form:input path="name" cssClass="form-control" />
							<form:errors path="name" cssClass="error" />
							<div class="error" id="error-name"></div>
						</div>
						<div class="form-group">
							<label for="createName">Người tạo</label>
							<c:choose>
								<c:when test="${clazz.id == null}">
									<form:input path="createName" cssClass="form-control" readonly="true" value="${username}" />
								</c:when>
								<c:otherwise>
									<form:input path="createName" cssClass="form-control" readonly="true" />
								</c:otherwise>
							</c:choose>
							<form:errors path="createName" cssClass="error" />
							<div class="error" id="error-createName"></div>
						</div>
						<div class="form-group">
							<label for="updateName">Người sửa</label>
							<form:input path="updateName" cssClass="form-control" readonly="true" value="${username}" />
							<form:errors path="updateName" cssClass="error" />
							<div class="error" id="error-updateName"></div>
						</div>
						<div class="form-group">
							<c:choose>
								<c:when test="${clazz.id == null}">
									<button class="btn btn-default action" id="insert" data-action="insert">Thêm mới</button>
									<a href="/class/insert" class="btn btn-default">Nhập lại</a>
								</c:when>
								<c:otherwise>
									<button class="btn btn-default action" data-action="update">Cập	nhật</button>
								</c:otherwise>
							</c:choose>
						</div>
					</form:form>
				</div>
				<div class="tab-pane" id="list">
					<table id="data-class" class="table table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<c:if test="${sessionScope['role'] =='ROLE_ADMIN'}">
									<th>Action</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="clazz" items="${pageClass.getContent()}">
								<tr>
									<td class="text">${clazz.id}</td>
									<td class="text">${clazz.name}</td>
									<td>
										<c:if test="${sessionScope['role'] != 'ROLE_USER'}">
											<a class='btn btn-success' href="class/edit/${clazz.id}">Sửa</a>
										</c:if>
										<c:if test="${sessionScope['role'] == 'ROLE_ADMIN'}">
											<a class='btn btn-danger' href="class/delete/${clazz.id}">Xóa</a>
										</c:if>
										
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<ul class="pagination col-sm-4 col-sm-push-4">
						<c:forEach begin="1" end="${pageClass.getTotalPages()}"
							varStatus="loop">
							<li><a href='/class/?page=${loop.index}&keyword=${keyword}'>${loop.index}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>


<jsp:include page="layout/_footer.jsp" />