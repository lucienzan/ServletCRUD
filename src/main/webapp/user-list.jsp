<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
</head>
<body>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
		crossorigin="anonymous">
<body>
	<div class="container">
		<div class="row justify-content-center align-item-center">
			<div class="col-8 mt-4">
				<div class="d-flex justify-content-between align-items-center mb-3">
					<h2>User Lists</h2>
					<a href="<%=request.getContextPath()%>/create"
						class="btn btn-primary">Create</a>
				</div>
				<div>
					<table class="table table-bordered">
						<thead>
							<tr>
								<th class="text-center">ID</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Age</th>
								<th class="text-center">Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="userModel" items="${listUser}" varStatus="loop">
								<tr>
									<td class="text-center"><c:out value="${loop.count}" /></td>
									<td><c:out value="${userModel.firstName}" /></td>
									<td><c:out value="${userModel.lastName}" /></td>
									<td><c:out value="${userModel.age}" /></td>
									<td class="text-center"><a
										href="edit?id=<c:out value='${userModel.id}' />"
										class="btn btn-warning">Edit</a> <a
										href="delete?id=<c:out value='${userModel.id}' />"
										class="btn btn-danger">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<p>Context Path: ${pageContext.request.contextPath}</p>
				</div>
			</div>
		</div>
	</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</html>