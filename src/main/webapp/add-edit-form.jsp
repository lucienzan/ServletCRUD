<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Create</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row mt-4 justify-content-center align-item-center">
			<div class="col-8">
				<div class="d-flex justify-content-between align-items-center">
					<c:if test="${user != null}">
						<h2>Edit User</h2>
					</c:if>
					<c:if test="${user == null}">
						<h2>Create User</h2>
					</c:if>
					<a href="<%=request.getContextPath()%>/list"
						class="btn btn-primary">User list</a>
				</div>
				<div>
					<c:choose>
						<c:when test="${user != null}">
							<form action="userUpdate" method="post">
								<c:if test="${user != null}">
									<input type="hidden" name="id"
										value="<c:out value='${user.getId()}' />" />
								</c:if>
								<div class="form-group mb-3">
									<label class="form-label" for="firstName">First Name</label> <input
										type="text" name="firstName" id="firstName"
										value="<c:out value='${user.getFirstName()}' />"
										class="form-control ${not empty requestScope.firstNameError ? 'is-invalid' : ''}">
									<c:if test="${requestScope.firstNameError != null}">
										<div class="invalid-feedback">
											<c:out value="${requestScope.firstNameError}" />
										</div>
									</c:if>
								</div>
								<div class="form-group mb-3">
									<label class="form-label" for="lastName">Last Name</label> <input
										type="text" name="lastName" id="lastName"
										value="<c:out value='${user.getLastName()}' />"
										class="form-control ${not empty requestScope.lastNameError ? 'is-invalid' : ''}">
									<c:if test="${requestScope.lastNameError != null}">
										<div class="invalid-feedback">
											<c:out value="${requestScope.lastNameError}" />
										</div>
									</c:if>
								</div>
								<div class="form-group mb-3">
									<label class="form-label" for="age">Age</label> <input
										type="number" name="age" id="age"
										value="<c:out value='${user.getAge() != null ? user.getAge() : 1 }'/>"
										min="1" max="120"
										class="form-control ${not empty requestScope.ageError ? 'is-invalid' : ''}">
									<c:if test="${requestScope.ageError != null}">
										<div class="invalid-feedback">
											<c:out value="${requestScope.ageError}" />
										</div>
									</c:if>
								</div>
								<button type="submit" class="btn btn-primary">Update</button>
							</form>
						</c:when>
						<c:otherwise>
							<form action="userCreate" method="post">
								<div class="form-group mb-3">
									<label class="form-label" for="firstName">First Name</label> <input
										type="text" name="firstName" id="firstName"
										class="form-control ${not empty requestScope.firstNameError ? 'is-invalid' : ''}">
									<c:if test="${requestScope.firstNameError != null}">
										<div class="invalid-feedback">
											<c:out value="${requestScope.firstNameError}" />
										</div>
									</c:if>
								</div>
								<div class="form-group mb-3">
									<label class="form-label" for="lastName">Last Name</label> <input
										type="text" name="lastName" id="lastName"
										class="form-control ${not empty requestScope.lastNameError ? 'is-invalid' : ''}">
									<c:if test="${requestScope.lastNameError != null}">
										<div class="invalid-feedback">
											<c:out value="${requestScope.lastNameError}" />
										</div>
									</c:if>
								</div>
								<div class="form-group mb-3">
									<label class="form-label" for="age">Age</label> <input
										type="number" name="age" id="age" value="1" min="1" max="120"
										class="form-control ${not empty requestScope.ageError ? 'is-invalid' : ''}">
									<c:if test="${requestScope.ageError != null}">
										<div class="invalid-feedback">
											<c:out value="${requestScope.ageError}" />
										</div>
									</c:if>
								</div>
								<button type="submit" class="btn btn-primary">Create</button>
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>
