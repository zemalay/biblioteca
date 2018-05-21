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
<title>Emprestimo Formulario</title>
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
			<h2 class="text-center">Cadastro do Emprestimo</h2>
			<form:form id="emprestimo-form" cssClass="container"
				onsubmit="return checkCampos();" modelAttribute="emprestimo"
				method="POST" action="/biblioteca/emprestimo/add">
				<div class="row" id="formulario">
					<div class="col-md-6">
						<form:label path="funcionario.id">ID Funcionario</form:label>
						<form:input id="idFunc" class="form-control" path="funcionario.id"
							readonly="true" />
						<form:label path="funcionario.nome">Funcionario</form:label>
						<form:input id="funcionario" class="form-control"
							path="funcionario.nome"
							placeholder="auto completo o nome funcionario" />


						<form:label path="aluno.id">ID Aluno</form:label>
						<form:input id="idAluno" class="form-control" path="aluno.id"
							readonly="true" />
						<form:label path="aluno.nome">Aluno</form:label>
						<form:input id="aluno" class="form-control" path="aluno.nome"
							placeholder="auto completo o nome aluno" />


						<form:label path="item.id">ID Item</form:label>
						<form:input id="idItem" class="form-control" path="item.id"
							readonly="true" />
						<form:label path="item.titulo">Item</form:label>
						<form:input id="item" class="form-control" path="item.titulo"
							placeholder="auto completo o titulo do item" />


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
		$('#funcionario').autocomplete({
			serviceUrl : '${pageContext.request.contextPath}/getFuncionarios',
			paramName : "funcionarioNome",
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
				$('#idFunc').val(ui.data);
				return false;
			}

		});

		$('#aluno').autocomplete({
			serviceUrl : '${pageContext.request.contextPath}/getAlunos',
			paramName : "alunoNome",
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
				$('#idAluno').val(ui.data);
				return false;
			}

		});

		$('#item').autocomplete({
			serviceUrl : '${pageContext.request.contextPath}/getItens',
			paramName : "itemTitulo",
			delimiter : ",",
			transformResult : function(response) {

				return {
					//must convert json to javascript object before process
					suggestions : $.map($.parseJSON(response), function(item) {
						return {
							value : item.titulo,
							data : item.id
						};
					})

				};

			},
			onSelect : function(ui) {
				console.log(ui.data);
				$('#idItem').val(ui.data);
				return false;
			}

		});

		function checkCampos() {
			var funcionario = $("#funcionario").val();
			var aluno = $("#aluno").val();
			var item = $("#item").val();

			if (funcionario === '' || aluno === '' || item === '') {
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
