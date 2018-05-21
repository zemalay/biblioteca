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
<title>Home</title>
</head>
<body>

	<div id="wrapper" style="background-color: darkcyan;">

		<!-- Sidebar -->
		<jsp:include page="menu.jsp"></jsp:include>

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<nav class="navbar"> <a class="navbar-brand btn btn-dark"
					href="#menu-toggle" id="menu-toggle"> <span
					class="glyphicon glyphicon-print"></span>Menu
				</a> </nav>
			</div>
		</div>

		<div class="modal-content">
			<c:if test="${!empty listaReserva }">
			<h3 style="margin-left: 40%;">Lista de Reservas</h3>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Código da Reserva</th>
							<th scope="col">Título do Item</th>
							<th scope="col">Matrícula do aluno</th>
							<th scope="col">Nome do aluno</th>
							<th scope="col">Data da Reservado</th>
							<th scope="col">Data a Pegar</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaReserva}" var="reserva">
							<tr>
								<th scope="row">${reserva.id}</th>
								<td>${reserva.item.titulo}</td>
								<td>${reserva.aluno.matricula}</td>
								<td>${reserva.aluno.nome}</td>
								<td>${reserva.dataReservado}</td>
								<td>${reserva.dataPegar}</td>
								<td><a style="color: red;"
										href="<c:url value='/reserva/cancelar/${reserva.id}' />"> cancelar
									</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/resources/izitoast/js/iziToast.min.js" />"></script>
	<script type="text/javascript">
	</script>
	<script type="text/javascript"
		src="<c:url value="/resources/javascript/action.js" />"></script>
</body>
</html>
