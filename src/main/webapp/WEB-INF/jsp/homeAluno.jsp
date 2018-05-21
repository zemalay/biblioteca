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
<script
	src="<c:url value="/resources/javascript/jquery/jquery-3.3.1.min.js" />"></script>
<script
	src="<c:url value="/resources/javascript/jquery/jquery.autocomplete.min.js" />"></script>
<script src="<c:url value="/resources/javascript/popper.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<link rel="stylesheet" href='<c:url value="/resources/css/style.css"/>' />
<title>Aluno</title>
</head>
<body>

	<div id="wrapper" style="background-color: darkcyan;">

		<!-- Sidebar -->
		<jsp:include page="menuAluno.jsp"></jsp:include>


		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<nav class="navbar"> <a class="navbar-brand btn btn-dark"
					href="#menu-toggle" id="menu-toggle"> Menu </a> </nav>
			</div>
		</div>
		<div class="modal-content">
			<h2 class="text-center">Seja Bem Vindo ${aluno.nome}</h2>

			<br>
			<c:if test="${!empty listaEmprestimo }">
				<h3 style="margin-left: 40%;">Seus Empréstimos</h3>
				<table class="table text-center">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Código do Emprestimo</th>
							<th scope="col">Título do Item</th>
							<th scope="col">Matrícula do aluno</th>
							<th scope="col">Nome do aluno</th>
							<th scope="col">Data da Entrega</th>
							<th scope="col">Data da Devolução</th>
							<th scope="col">QTD de Renovacao</th>
							<th scope="col">Devolução</th>
							<th scope="col">Entregou</th>
							<th scope="col">Renovacao</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaEmprestimo}" var="emprestimo">
							<tr>
								<th scope="row">${emprestimo.id}</th>
								<td>${emprestimo.item.titulo}</td>
								<td>${emprestimo.item.titulo}</td>
								<td>${emprestimo.aluno.matricula}</td>
								<td>${emprestimo.aluno.nome}</td>
								<td>${emprestimo.dataCadastrado}</td>
								<td>${emprestimo.dataDevolucao}</td>
								<td>${emprestimo.renovacao}</td>
								<c:if test="${emprestimo.entregou == true}">
									<td>sim</td>
								</c:if>
								<c:if test="${emprestimo.entregou == false}">
									<td>nao</td>
								</c:if>
								<c:if test="${emprestimo.entregou == false}">
									<td><a style="color: red;"
										href="<c:url value='/aluno/emprestimo/renovar/${emprestimo.id}' />">
											renovar </a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<br>
			<c:if test="${!empty listaDivida }">
				<h3 style="margin-left: 40%;">Suas das Dividas</h3>
				<table class="table text-center">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Código de Divida</th>
							<th scope="col">Título do Item</th>
							<th scope="col">Matrícula do aluno</th>
							<th scope="col">Nome do aluno</th>
							<th scope="col">Valor</th>
							<th scope="col">Pagou</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaDivida}" var="divida">
							<tr>
								<th scope="row">${divida.id}</th>
								<td>${divida.emprestimo.item.titulo}</td>
								<td>${divida.aluno.matricula}</td>
								<td>${divida.aluno.nome}</td>
								<td>${divida.saldo}</td>
								<c:if test="${divida.pago == true}">
									<td>sim</td>
								</c:if>
								<c:if test="${divida.pago == false}">
									<td>nao</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<br>
			<c:if test="${!empty listaReserva }">
				<h3 style="margin-left: 40%;">Suas Reservas</h3>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Código da Reserva</th>
							<th scope="col">Título do Item</th>
							<th scope="col">Matrícula do aluno</th>
							<th scope="col">Nome do aluno</th>
							<th scope="col">Data da Reservado</th>
							<th scope="col">Data a Pegar</th>
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
		var mensagem = '${mensagem}';
		if (mensagem != "") {
			iziToast.show({
				title : 'Erro',
				message : mensagem,
				color : 'red',
				timeout : false,
				position : 'topRight'
			});
		}
	</script>
	<script type="text/javascript"
		src="<c:url value="/resources/javascript/action.js" />"></script>
</body>
</html>
