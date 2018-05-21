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
			<h3 style="margin-left: 40%;">Lista de Itens da Biblioteca</h3>
			<c:if test="${!empty listaItem }">
				<table class="table text-center">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Tipo</th>
							<th scope="col">Titulo</th>
							<th scope="col">ISBN</th>
							<th scope="col">Editora</th>
							<th scope="col">Tipo Anal de Congresso</th>
							<th scope="col">Tipo Midia</th>
							<th scope="col">Tipo TCC</th>
							<th scope="col">Autor</th>
							<th scope="col">Quantidade</th>
							<th scope="col">Data</th>
							<th scope="col">Data Gravacao</th>
							<th scope="col">Orientador</th>
							<c:if test="${funcionarioLogado.tipoFunc == 'ADMINISTRADOR' }">
								<th scope="col">Actions</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:if test="${funcionarioLogado.tipoFunc == 'ADMINISTRADOR' }">
							<c:forEach items="${listaItem}" var="item">
								<tr>
									<th scope="row"><a
										href="<c:url value='/item/${item.id}' />">${item.id}</a></th>
									<td>${item.tipoItem}</td>
									<td>${item.titulo}</td>
									<td>${item.isbn}</td>
									<td>${item.editora}</td>
									<td>${item.tipoAnais}</td>
									<td>${item.tipoMidia}</td>
									<td>${item.tipoTrabalho}</td>
									<td>${item.autor}</td>
									<td>${item.quantidade}</td>
									<td>${item.data}</td>
									<td>${item.dataGravacao}</td>
									<td>${item.orientador}</td>
									<td><a style="color: red;"
										href="<c:url value='/item/delete/${item.id}' />"> apagar </a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${funcionarioLogado.tipoFunc == 'OPERADOR' }">
							<c:forEach items="${listaItem}" var="item">
								<tr>
									<th scope="row"><a
										href="<c:url value='/item/${item.id}' />">${item.id}</a></th>
									<td>${item.tipoItem}</td>
									<td>${item.titulo}</td>
									<td>${item.isbn}</td>
									<td>${item.editora}</td>
									<td>${item.tipoAnais}</td>
									<td>${item.tipoMidia}</td>
									<td>${item.tipoTrabalho}</td>
									<td>${item.autor}</td>
									<td>${item.quantidade}</td>
									<td>${item.data}</td>
									<td>${item.dataGravacao}</td>
									<td>${item.orientador}</td>
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
