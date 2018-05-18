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
<title>Funcionario Form</title>
</head>
<body>

	<div id="wrapper" style="background-color: darkcyan;">

		<!-- Sidebar -->
		<div id="sidebar-wrapper" style="background-color: darkcyan;">
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a
					href="<c:url value='/funcionario/home' />" style="color: white;">
						Biblioteca-UFAB </a></li>

				<li>
					<div class="dropdown">
						<button class="btn btn-dark dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Item da
							Biblioteca</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="<c:url value='/Item/lista' />">Listar</a>
							<a class="dropdown-item" href="<c:url value='/Item/form' />">Cadastrar</a>

						</div>
					</div>
				<li>
					<div class="dropdown">
						<button class="btn btn-dark dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Aluno(a)</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="<c:url value='/aluno/lista' />">Listar</a>
							<a class="dropdown-item" href="<c:url value='/aluno/form' />">Cadastrar</a>

						</div>
					</div>
				</li>
				<li>
					<div class="dropdown">
						<button class="btn btn-dark dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							Funcionário(a)</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item"
								href="<c:url value='/funcionario/lista' />">Listar</a> <a
								class="dropdown-item" href="<c:url value='/funcionario/form' />">Cadastrar</a>
						</div>
					</div>
				</li>
				<li>
					<div class="dropdown">
						<button class="btn btn-dark dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Empréstimo</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="#">Listar</a> <a
								class="dropdown-item" href="#">Cadastrar</a> <a
								class="dropdown-item" href="#"></a>
						</div>
					</div>
					<div class="dropdown">
						<button class="btn btn-dark dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Reservar</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="#">Listar</a> <a
								class="dropdown-item" href="#">Cadastrar</a> <a
								class="dropdown-item" href="#"></a>
						</div>
					</div>
				</li>
				<li><a href="#" style="color: white;">Dívida</a></li>
				<li><a href="#" style="color: white;">Configuração</a></li>

				<li><a href="#" style="color: white;">Sobre</a></li>

			</ul>
		</div>



		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<nav class="navbar"> <a class="navbar-brand btn btn-dark"
					href="#menu-toggle" id="menu-toggle"> Menu
				</a> </nav>
			</div>
		</div>
		<div class="modal-content">
			<h2 class="text-center">Cadastro de Funcionário(a)</h2>
			<form:form id="func-form" onsubmit="return checkCampos();" cssClass="container"
				modelAttribute="funcionario" method="POST"
				action="/biblioteca/funcionario/add">
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
						<form:label path="naturalidade">Naturalidade</form:label>
						<form:input class="form-control" path="naturalidade"
							placeholder="naturalidade" />
						<form:label path="telefone">Telefone</form:label>
						<form:input class="form-control" path="telefone"
							placeholder="telefone" />
						<form:label path="email">Email</form:label>
						<form:input class="form-control" path="email" placeholder="email" />
						<form:label path="senha">Senha</form:label>
						<form:password class="form-control" path="senha"
							placeholder="senha" />
						<div class="dropdown">
							<div class="form-group">
								<form:label path="tipoFunc">Tipo Funcionario</form:label>
								<form:select path="tipoFunc">
									<form:option value="">Selecione</form:option>
									<form:option value="ADMINISTRADOR">${funcionario.tipoFunc}</form:option>
									<form:option value="OPERADOR">${funcionario.tipoFunc}</form:option>
								</form:select>
								<button type="submit" class="btn btn-primary"
									style="margin-top: 9%; margin-left: 5%;">Cadastrar</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>

	</div>
	<script type="text/javascript"
		src="<c:url value="/resources/izitoast/js/iziToast.min.js" />"></script>
	<script type="text/javascript">
		function checkCampos(){
			var nome = $("#nome").val();
			var usuario = $("#usuario").val();
			var endereco = $("#endereco").val();
			var cpf = $("#cpf").val();
			var rg = $("#rg").val();
			var naturalidade = $("#naturalidade").val();
			var telefone = $("#telefone").val();
			var email = $("#email").val();
			var senha = $("#senha").val();
			var tipoFunc = $("#tipoFunc").val();
			
			if(nome === '' || usuario === '' || endereco === '' || cpf === '' || rg === '' || naturalidade === '' || telefone === '' || email === '' || senha === '' || tipoFunc === ''){
				iziToast.show({
			        title: 'Erro',
			        message: 'Devem preencher todos os campos',
			        color: 'red',
			        timeout: 5000,
			        position: 'topCenter'
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
