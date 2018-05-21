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
<title>Funcionario Formulario</title>
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
			<h2 class="text-center">Cadastro de Curso</h2>
			<form:form id="curso-form" onsubmit="return checkCampos();"
				cssClass="container" modelAttribute="curso" method="POST"
				action="/biblioteca/curso/add">
				<div class="row" id="formulario">
					<div class="col-md-6">
						<form:label path="nome">Nome do Curso</form:label>
						<form:input class="form-control" path="nome"
							placeholder="nome do curso" />
						<form:label path="area">Area</form:label>
						<form:input class="form-control" path="area" placeholder="area" />
						<div class="form-group">
							<form:label path="nivel">Nivel</form:label>
							<form:select path="nivel">
								<form:option value="">Selecione</form:option>
								<form:option value="GRADUACAO">${curso.nivel}</form:option>
								<form:option value="ESPECIALIZACAO">${curso.nivel}</form:option>
								<form:option value="MESTRADO">${curso.nivel}</form:option>
								<form:option value="DOUTORADO">${curso.nivel}</form:option>
							</form:select>
							<button type="submit" class="btn btn-primary"
								style="margin-top: 9%; margin-left: 5%;">Cadastrar</button>
						</div>

					</div>
				</div>
			</form:form>
		</div>

	</div>
	<script type="text/javascript"
		src="<c:url value="/resources/izitoast/js/iziToast.min.js" />"></script>
	<script type="text/javascript">
		function checkCampos() {
			var nome = $("#nome").val();
			var area = $("#area").val();
			var nivel = $("#nivel").val();

			if (nome === '' || area === '' || nivel === 'NONE') {
				iziToast.show({
					title : 'Erro',
					message : 'Devem preencher todos os campos',
					color : 'red',
					timeout : 5000,
					position : 'topCenter'
				});
				return false;
			}
			return true;
		}
	</script>
	<script type="text/javascript"
		src="<c:url value="/resources/javascript/action.js" />"></script>
</body>
</html>
