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
<title>Funcionario Detail</title>
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
			<h2 style="margin-left: 38%;">Detalhes de Funcionário(a)</h2>
			<form:form id="func-atualizar" cssStyle="margin-left: 20%;"
				modelAttribute="funcionario" method="POST"
				action="/biblioteca/funcionario/update">
				<div class="row" id="formulario">
					<div class="col-md-4">
						<form:label path="nome">Nome Completo</form:label>
						<form:input class="form-control" path="nome"
							placeholder="nome completo" />
						<form:label path="usuario">Usuario</form:label>
						<form:input class="form-control" path="usuario"
							placeholder="usuario" />
						<form:label path="endereco">Endereco</form:label>
						<form:input class="form-control" path="endereco"
							placeholder="endereco" />
						<form:label path="cpf">CPF</form:label>
						<form:input class="form-control" path="cpf" placeholder="cpf" />
						<form:label path="rg">RG</form:label>
						<form:input class="form-control" path="rg" placeholder="rg" />

					</div>
					<div class="col-md-4">
						<c:if test="${funcionarioLogado.id == funcionario.id }">
							<form:label path="senha">Senha</form:label>
							<form:input class="form-control" path="senha" readonly="false" />
						</c:if>
						
						<c:if test="${funcionarioLogado.id != funcionario.id }">
							<form:hidden class="form-control" path="senha" readonly="true" />
						</c:if>
						<form:label path="id">ID</form:label>
						<form:input class="form-control" path="id" readonly="true" />
						<form:label path="naturalidade">Naturalidade</form:label>
						<form:input class="form-control" path="naturalidade"
							placeholder="naturalidade" />
						<form:label path="telefone">Telefone</form:label>
						<form:input class="form-control" path="telefone"
							placeholder="telefone" />
						<form:label path="email">Mail</form:label>
						<form:input class="form-control" path="email" placeholder="email" />
						<div class="form-group">
							<form:label path="tipoFunc">Tipo Funcionario</form:label>
							<form:select cssClass="form-control" path="tipoFunc">
								<form:option value="">Selecione</form:option>
								<form:option value="ADMINISTRADOR">Administrador(a)</form:option>
								<form:option value="OPERADOR">Operador(a)</form:option>
							</form:select>
						</div>
						<button type="submit" class="btn btn-warning"
							style="margin-top: 9%; margin-left: 5%;">Atualizar</button>
					</div>
				</div>
			</form:form>

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
