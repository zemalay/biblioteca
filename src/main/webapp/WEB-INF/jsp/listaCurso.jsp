<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href='<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>' />
<link rel="stylesheet"
	href='<c:url value="/resources/izitoast/css/iziToast.min.css"/>' />
<link rel="stylesheet" href='<c:url value="/resources/css/style.css"/>' />
<script
	src="<c:url value="/resources/javascript/jquery/jquery-3.3.1.min.js" />"></script>
<script src="<c:url value="/resources/javascript/popper.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<title>Cursos</title>
</head>
<body>

	<div id="wrapper" style="background-color: darkcyan;">

		<!-- Sidebar -->
		<jsp:include page="menu.jsp"></jsp:include>
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<nav class="navbar"> <a class="navbar-brand btn btn-dark"
					href="#menu-toggle" id="menu-toggle"> Menu </a> </nav>
			</div>
		</div>

		<div class="modal-content">
			<h3 style="margin-left: 40%;">Lista de Cursos</h3>
			<c:if test="${!empty listaCurso }">
				<table class="table text-center">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Nome</th>
							<th scope="col">Area</th>
							<th scope="col">Nivel</th>
							<c:if test="${funcionarioLogado.tipoFunc == 'ADMINISTRADOR' }">
								<th scope="col">Actions</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:if test="${funcionarioLogado.tipoFunc == 'ADMINISTRADOR' }">
							<c:forEach items="${listaCurso}" var="curso">
								<tr>
									<th scope="row">${curso.id}</th>
									<td>${curso.nome}</td>
									<td>${curso.area}</td>
									<td>${curso.nivel}</td>
									<td><a style="color: red;"
										href="<c:url value='/curso/delete/${curso.id}' />"> apagar
									</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${funcionarioLogado.tipoFunc == 'OPERADOR' }">
							<c:forEach items="${listaCurso}" var="curso">
								<tr>
									<th scope="row">${curso.id}</th>
									<td>${curso.nome}</td>
									<td>${curso.area}</td>
									<td>${curso.nivel}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>



			</c:if>
		</div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/resources/izitoast/js/iziToast.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/javascript/action.js" />"></script>
</body>
</html>
