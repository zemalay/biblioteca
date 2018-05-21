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
<title>Aluno</title>
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
			<h3 style="margin-left: 40%;">Lista de Aluno</h3>
			<c:if test="${!empty listaAluno }">
				<table class="table text-center">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Matricula</th>
							<th scope="col">Nome</th>
							<th scope="col">RG</th>
							<th scope="col">CPF</th>
							<th scope="col">Naturalidade</th>
							<th scope="col">Endereco</th>
							<th scope="col">Telefone</th>
							<th scope="col">Ano</th>
							<th scope="col">Periodo de Ingresso</th>
							<th scope="col">Email</th>
							<th scope="col">Curso</th>
							<c:if test="${funcionarioLogado.tipoFunc == 'ADMINISTRADOR' }">
								<th scope="col">Actions</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:if test="${funcionarioLogado.tipoFunc == 'ADMINISTRADOR' }">
							<c:forEach items="${listaAluno}" var="aluno">
								<tr>
									<th scope="row"><a
										href="<c:url value='/aluno/${aluno.id}' />">${aluno.id}</a></th>
									<td>${aluno.matricula}</td>
									<td>${aluno.nome}</td>
									<td>${aluno.rg}</td>
									<td>${aluno.cpf}</td>
									<td>${aluno.naturalidade}</td>
									<td>${aluno.endereco}</td>
									<td>${aluno.telefone}</td>
									<td>${aluno.ano}</td>
									<td>${aluno.periodoIngresso}</td>
									<td>${aluno.email}</td>
									<td>${aluno.curso.nome}</td>
									<td><a style="color: red;"
										href="<c:url value='/aluno/delete/${aluno.id}' />"> apagar
									</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${funcionarioLogado.tipoFunc == 'OPERADOR' }">
							<c:forEach items="${listaAluno}" var="aluno">
								<tr>
									<th scope="row"><a
										href="<c:url value='/aluno/${aluno.id}' />">${aluno.id}</a></th>
									<td>${aluno.matricula}</td>
									<td>${aluno.nome}</td>
									<td>${aluno.rg}</td>
									<td>${aluno.cpf}</td>
									<td>${aluno.naturalidade}</td>
									<td>${aluno.endereco}</td>
									<td>${aluno.telefone}</td>
									<td>${aluno.ano}</td>
									<td>${aluno.periodoIngresso}</td>
									<td>${aluno.email}</td>
									<td>${aluno.curso.nome}</td>
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
	<script type="text/javascript">
		var mensagem = '${mensagem}';
		console.log("mensagem :" + mensagem);
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
