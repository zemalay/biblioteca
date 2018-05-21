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
<title>Aluno Formulario</title>
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
			<h2 class="text-center">Cadastro de Aluno(a)</h2>
			<form:form id="aluno-form" cssClass="container" onsubmit="return checkCampos();"
				modelAttribute="aluno" method="POST" action="/biblioteca/aluno/add">
				<div class="row" id="formulario">
					<div class="col-md-4">
						<form:label path="nome">Nome Completo</form:label>
						<form:input class="form-control" path="nome"
							placeholder="nome completo" />
						<form:label path="endereco">Endereco</form:label>
						<form:input class="form-control" path="endereco"
							placeholder="endereco" />
						<form:label path="cpf">CPF</form:label>
						<form:input class="form-control" path="cpf" placeholder="cpf" />
						<form:label path="rg">RG</form:label>
						<form:input class="form-control" path="rg" placeholder="rg" />
						<form:label path="ano">Ano</form:label>
						<form:input class="form-control" path="ano" placeholder="ano" />
						<form:label path="nomeMae">Mae</form:label>
						<form:input class="form-control" path="nomeMae" placeholder="nome da mae" />
					</div>
					<div class="col-md-4">
						<form:label path="naturalidade">Naturalidade</form:label>
						<form:input class="form-control" path="naturalidade"
							placeholder="naturalidade" />
						<form:label path="curso.nome">Curso</form:label>
						<form:input id="curso" class="form-control" path="curso.nome"
							placeholder="autocompleto digite o curso" />
						<form:label path="curso.id">ID</form:label>
						<form:input id="idCurso" class="form-control" path="curso.id" readonly="true"/>
						<form:label path="telefone">Telefone</form:label>
						<form:input autocomplete="" class="form-control" path="telefone"
							placeholder="telefone" />
						<form:label path="email">Email</form:label>
						<form:input class="form-control" path="email" placeholder="email" />
						<form:label path="senha">Senha</form:label>
						<form:password class="form-control" path="senha"
							placeholder="senha" />
						<button type="submit" class="btn btn-primary"
							style="margin-top: 9%; margin-left: 5%;">Cadastrar</button>

					</div>
				</div>
			</form:form>
		</div>

	</div>
	<script type="text/javascript"
		src="<c:url value="/resources/izitoast/js/iziToast.min.js" />"></script>
	<script type="text/javascript">
		$('#curso').autocomplete({
			serviceUrl : '${pageContext.request.contextPath}/getCursos',
			paramName : "cursoNome",
			delimiter : ",",
			transformResult : function(response) {

				return {
					//must convert json to javascript object before process
					suggestions : $.map($.parseJSON(response), function(item) {
						return {
							value : item.nome,
							data : item.id
						};
					})

				};

			},
			onSelect : function(ui) {
				console.log(ui.data);
				$('#idCurso').val(ui.data);
				return false;
			}

		});
		function checkCampos() {
			var nome = $("#nome").val();
			var usuario = $("#usuario").val();
			var endereco = $("#endereco").val();
			var cpf = $("#cpf").val();
			var rg = $("#rg").val();
			var naturalidade = $("#naturalidade").val();
			var telefone = $("#telefone").val();
			var email = $("#email").val();
			var senha = $("#senha").val();
			var curso = $("#curso").val();

			if (nome === '' || usuario === '' || endereco === '' || cpf === ''
					|| rg === '' || naturalidade === '' || telefone === ''
					|| email === '' || senha === '' || curso === '') {
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
