<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="layout/_header.jsp" />

<img id="loading" src="/views/images/loading.gif">
<div class="student-content">
	<div class="container">
		<h2 class="text-center">QUẢN LÝ HỌC SINH</h2>
		<div class="row">
			<div class="input-group col-md-6 col-md-push-6">
				<input type="text" id="search" class="form-control"	placeholder="Name, Code, Address, Name of class" value="${keyword}"
					onkeyup="searchStudentAjax()"/> 
				<span class="input-group-btn"> <a class="btn btn-info"
					onclick="searchStudent()"> <i
						class="glyphicon glyphicon-search"></i>
				</a>
				</span>
			</div>
			<div class="search-result col-md-5 col-md-push-6">
				<table id="data-student" class="table table-hover">
					<thead>
						<tr>
							<th>Name</th>
							<th>Code</th>
							<th>Address</th>
						</tr>
					</thead>
					<tbody id="search-result">
					</tbody>
				</table>
				<p  class="text-center"><a href="#" onclick="viewMoreResult(event)">Xem thêm</a></p>
			</div>
		</div>
		<!-- Nav tabs -->
		<div class="row">
				<ul class="nav nav-tabs" role="tablist">
					<c:if test="${role !='USER' }">
						<li class="active" id="tab-update"><a href="#edit" role="tab" data-toggle="tab">CẬP NHẬT</a></li>
					</c:if>
					<li id="tab-list"><a href="#list" role="tab" data-toggle="tab">DANH SÁCH</a></li>
				</ul>
				<!-- Tab panes -->
				<div class="tab-content">
					<c:if test="${role!= 'USER'}">
						<div class="tab-pane active" id="edit">
							${message}
							<form:form action="student/index" commandName="student" role="form" onsubmit="return confirmForChanges();">
								<form:hidden path="id" cssClass="form-control" />
								<div class="form-group">
									<label for="name">Tên</label>
									<form:input path="name" cssClass="form-control" />
									<form:errors path="name" cssClass="error"/>
									<div class="error" id="error-name"></div>
								</div>
								<div class="form-group">
									<label for="createName">Người tạo</label>
									 <c:choose>
							        	<c:when test="${student.id == null}">
							            	<form:input path="createName" cssClass="form-control" readonly="true" value ="${username}"/>
							           	</c:when>
							           	<c:otherwise>
							            	<form:input path="createName" cssClass="form-control" readonly="true" />
							           	</c:otherwise>
							        </c:choose>
									<form:errors path="createName" cssClass="error"/>
									<div class="error" id="error-createName"></div>
								</div>
								<div class="form-group">
									<label for="updateName">Người sửa</label>
									<form:input path="updateName" cssClass="form-control" readonly="true" value ="${username}"/>
									<form:errors path="updateName" cssClass="error"/>
										<div class="error" id="error-updateName"></div>
								</div>
								<div class="form-group">
									<label for="code">Mã</label>
									<form:input path="code" cssClass="form-control" />
									<form:errors path="code" cssClass="error"/>
									<div class="error" id="error-code"></div>
								</div>
								<div class="form-group">
									<label for="address">Địa chỉ</label>
									<form:input path="address" cssClass="form-control" />
									<form:errors path="address" cssClass="error"/>
									<div class="error" id="error-address"></div>
								</div>
	
								<div class="form-group">
									<label for="averageScore">Điểm trung bình</label>
									<form:input path="averageScore" cssClass="form-control" />
									<form:errors path="averageScore" cssClass="error"/>
									<div class="error" id="error-averageScore"></div>
								</div>
								<div class="form-group">
									<label for="dateOfBirth">Ngày sinh</label>
									<form:input path="dateOfBirth" name="dateOfBirth"
										placeholder="DD/MM/YYYY" id="dateOfBirth" type="text"
										readonly="true" />
									<form:errors path="dateOfBirth" cssClass="error" />
									<div class="error" id="error-dateOfBirth"></div>
								</div>
								<div class="form-group">
									<label>Lớp</label>
									<form:checkboxes items="${allClasses}" path="classes" itemLabel="name" itemValue="id"/>
								</div>				  
								<div class="form-group">
									 <c:choose>
							        	<c:when test="${student.id == null}">
							            	<button class="btn btn-default action" id="insert" data-action="insert">Thêm mới</button>
							            	<a href="/student/insert" class="btn btn-default">Nhập lại</a>
							           	</c:when>
							           	<c:otherwise>
							            	<button class="btn btn-default action" data-action="update">Cập	nhật</button>
							           	</c:otherwise>
							        </c:choose>
								</div>
							</form:form>
						</div>
					</c:if>
					<div class="tab-pane" id="list">
						<table id="data-student" class="table table-hover page">
							<thead>
								<tr>
									<th>ID</th>
									<th>Name</th>
									<th>Code</th>
									<th>Date of birth</th>
									<th>Average Score</th>
									<th>Address</th>
									<c:if test="${sessionScope['role'] !='USER'}">
										<th>Action</th>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="student" items="${pageStudent.getContent()}">
									<tr>
										<td class="">${student.id}</td>
										<td class="text">${fn:escapeXml(student.name)}</td>
										<td class="text">${student.code}</td>
										<td class="text date">${student.dateOfBirth}</td>
										<td class="text">${student.averageScore}</td>
										<td class="text">${fn:escapeXml(student.address)}</td>
										<td><c:if test="${sessionScope['role'] != 'USER'}">
												<a class='btn btn-success' href="student/edit/${student.id}">Sửa</a>
												<c:if test="${sessionScope['role'] == 'ADMIN' }">
													<a class='btn btn-danger' href="student/delete/${student.id}">Xóa</a>
												</c:if>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<ul class="pagination col-sm-4 col-sm-push-4">
							<c:forEach begin="1" end="${pageStudent.getTotalPages()}"
								varStatus="loop">
							<c:choose>
								<c:when test="${sessionScope['page'] == loop.index}">
									<li class="active"><a onclick="disableChange(event)"
										href='/student/?page=${loop.index}&keyword=${keyword}'>${loop.index}</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href='/student/?page=${loop.index}&keyword=${keyword}'>${loop.index}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						</ul>
					</div>
				</div>
		</div>
	</div>
</div>

<jsp:include page="layout/_footer.jsp" />