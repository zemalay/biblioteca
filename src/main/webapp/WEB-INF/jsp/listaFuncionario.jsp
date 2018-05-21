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
<title>Funcionarios</title>
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
			<h3 style="margin-left: 40%;">Lista de Funcionários</h3>
			<c:if test="${!empty listaFuncionario }">
				<table class="table text-center">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Nome</th>
							<th scope="col">Tipo de Funcionário</th>
							<th scope="col">CPF</th>
							<th scope="col">RG</th>
							<th scope="col">Naturalidade</th>
							<th scope="col">Endereco</th>
							<th scope="col">Telefone</th>
							<th scope="col">Email</th>
							<c:if test="${funcionarioLogado.tipoFunc == 'ADMINISTRADOR' }">
								<th scope="col">Actions</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:if test="${funcionarioLogado.tipoFunc == 'ADMINISTRADOR' }">
							<c:forEach items="${listaFuncionario}" var="funcionario">
								<tr>
									<c:if test="${funcionario.tipoFunc == 'OPERADOR' }">
										<th scope="row"><a
											href="<c:url value='/funcionario/${funcionario.id}' />">${funcionario.id}</a>
										</th>
									</c:if>
									<c:if test="${funcionarioLogado.id == funcionario.id }">
										<th scope="row"><a
											href="<c:url value='/funcionario/${funcionario.id}' />">${funcionario.id}</a>
										</th>
									</c:if>
									<c:if
										test="${funcionario.tipoFunc == 'ADMINISTRADOR' && funcionarioLogado.id != funcionario.id }">
										<th scope="row">${funcionario.id}</th>
									</c:if>
									<td>${funcionario.nome}</td>
									<td>${funcionario.tipoFunc}</td>
									<td>${funcionario.cpf}</td>
									<td>${funcionario.rg}</td>
									<td>${funcionario.naturalidade}</td>
									<td>${funcionario.endereco}</td>
									<td>${funcionario.telefone}</td>
									<td>${funcionario.email}</td>
									<c:if test="${funcionario.tipoFunc == 'OPERADOR' }">
										<td><a style="color: red;"
											href="<c:url value='/funcionario/delete/${funcionario.id}' />">
												apagar </a></td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${funcionarioLogado.tipoFunc == 'OPERADOR' }">
							<c:forEach items="${listaFuncionario}" var="funcionario">
								<tr>
									<c:if test="${funcionarioLogado.id == funcionario.id }">
										<th scope="row"><a
											href="<c:url value='/funcionario/${funcionario.id}' />">${funcionario.id}</a>
										</th>
									</c:if>
									<c:if
										test="${funcionarioLogado.id != funcionario.id }">
										<th scope="row">${funcionario.id}</th>
									</c:if>
									<td>${funcionario.nome}</td>
									<td>${funcionario.tipoFunc}</td>
									<td>${funcionario.cpf}</td>
									<td>${funcionario.rg}</td>
									<td>${funcionario.naturalidade}</td>
									<td>${funcionario.endereco}</td>
									<td>${funcionario.telefone}</td>
									<td>${funcionario.email}</td>
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
