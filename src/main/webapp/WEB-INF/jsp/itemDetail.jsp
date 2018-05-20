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
<title>Item Formulario</title>
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
			<h2 class="text-center">Cadastro do Item</h2>
			<form:form id="item-form" onsubmit="return checkCampos();"
				cssClass="container" modelAttribute="item" method="POST"
				action="/biblioteca/item/update">
				<div class="row" id="formulario">
					<div class="col-md-4">
						<form:label path="id">ID Item</form:label>
						<form:input id="idItem" class="form-control" path="id"
							readonly="true" />
						<div class="form-group">
							<form:label path="tipoItem">Tipo Item</form:label>
							<form:select class="form-control" path="tipoItem">
								<form:option value="">Selecione</form:option>
								<form:option value="ANAIS">${item.tipoItem}</form:option>
								<form:option value="JORNAL">${item.tipoItem}</form:option>
								<form:option value="LIVRO">${item.tipoItem}</form:option>
								<form:option value="MIDIA">${item.tipoItem}</form:option>
								<form:option value="REVISTA">${item.tipoItem}</form:option>
								<form:option value="TCC">${item.tipoItem}</form:option>
							</form:select>
						</div>

						<div class="form-group">
							<form:label path="tipoAnais">Tipo Anais Congresso</form:label>
							<form:select class="form-control" path="tipoAnais">
								<form:option value="">Selecione</form:option>
								<form:option value="ARTIGO">${item.tipoAnais}</form:option>
								<form:option value="POSTER">${item.tipoAnais}</form:option>
								<form:option value="RESUMO">${item.tipoAnais}</form:option>
								<form:option value="NONE">${item.tipoAnais}</form:option>
							</form:select>
						</div>

						<div class="form-group">
							<form:label path="tipoMidia">Tipo Midia</form:label>
							<form:select class="form-control" path="tipoMidia">
								<form:option value="">Selecione</form:option>
								<form:option value="CD">${item.tipoMidia}</form:option>
								<form:option value="DVD">${item.tipoMidia}</form:option>
								<form:option value="NONE">${item.tipoMidia}</form:option>
							</form:select>
						</div>


						<div class="form-group">
							<form:label path="tipoTrabalho">Tipo TCC</form:label>
							<form:select class="form-control" path="tipoTrabalho">
								<form:option value="">Selecione</form:option>
								<form:option value="MONOGRAFIA">${item.tipoTrabalho}</form:option>
								<form:option value="DISSERTACAO">${item.tipoTrabalho}</form:option>
								<form:option value="TESE">${item.tipoTrabalho}</form:option>
								<form:option value="NONE">${item.tipoTrabalho}</form:option>
							</form:select>
						</div>

						<form:label path="titulo">Titulo</form:label>
						<form:input class="form-control" path="titulo"
							placeholder="Titulo" />
						<form:label path="isbn">ISBN</form:label>
						<form:input class="form-control" path="isbn" placeholder="ISBN" />
						<form:label path="autor">Autor</form:label>
						<form:input class="form-control" path="autor" placeholder="autor" />
						<form:label path="congresso">Congresso</form:label>
						<form:input class="form-control" path="congresso"
							placeholder="congresso" />
						<form:label path="anoPublicacao">Ano Publicacao</form:label>
						<form:input class="form-control" path="anoPublicacao"
							placeholder="anoPublicacao" />
					</div>
					<div class="col-md-4">
						<form:label path="local">Local</form:label>
						<form:input class="form-control" path="local" placeholder="local" />
						<form:label path="editora">Editora</form:label>
						<form:input class="form-control" path="editora"
							placeholder="editora" />
						<form:label path="edicao">Edicao</form:label>
						<form:input class="form-control" path="edicao"
							placeholder="edicao" />
						<form:label path="numeroPagina">Nº Pagina</form:label>
						<form:input class="form-control" path="numeroPagina"
							placeholder="numeroPagina" />
						<form:label path="area">Area</form:label>
						<form:input class="form-control" path="area" placeholder="area" />
						<form:label path="tema">Tema</form:label>
						<form:input class="form-control" path="tema" placeholder="tema" />
						<form:label path="dataGravacao">Data Gravacao</form:label>
						<form:input class="form-control" path="dataGravacao"
							placeholder="dataGravacao" />
						<form:label path="orientador">Orientador</form:label>
						<form:input class="form-control" path="orientador"
							placeholder="orientador" />
						<form:label path="data">Data</form:label>
						<form:input class="form-control" path="data" placeholder="data" />
						<form:label path="quantidade">Quantidade</form:label>
						<form:input class="form-control" path="quantidade"
							placeholder="quantidade" />
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
			var tipoFunc = $("#tipoFunc").val();

			if (nome === '' || usuario === '' || endereco === '' || cpf === ''
					|| rg === '' || naturalidade === '' || telefone === ''
					|| email === '' || senha === '' || tipoFunc === '') {
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
