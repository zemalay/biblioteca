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
		<jsp:include page="menu.jsp"></jsp:include>


		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<nav class="navbar"> <a class="navbar-brand btn btn-dark"
					href="#menu-toggle" id="menu-toggle"> Menu </a> </nav>
			</div>
		</div>
		<div class="modal-content">
			<h2 class="text-center">${aluno.nome }</h2>
			<form:form id="aluno-form" cssClass="container"
				onsubmit="return checkCampos();" modelAttribute="aluno"
				method="POST" action="/biblioteca/aluno/update">
				<div class="row" id="formulario">
					<div class="col-md-4">
						<form:label path="id">ID</form:label>
						<form:input class="form-control" path="id" placeholder="id"
							readonly="true" />
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
						<form:input class="form-control" path="nomeMae"
							placeholder="nome da mae" />
						<form:input class="form-control" path="senha" readonly="true" />
					</div>
					<div class="col-md-4">
						<form:label path="matricula">Matricula</form:label>
						<form:input class="form-control" path="matricula" readonly="true" />
						<form:label path="periodoIngresso">Periodo</form:label>
						<form:input class="form-control" path="periodoIngresso"
							readonly="true" />
						<form:label path="naturalidade">Naturalidade</form:label>
						<form:input class="form-control" path="naturalidade"
							placeholder="naturalidade" />
						<form:label path="curso.nome">Curso</form:label>
						<form:input id="curso" class="form-control" path="curso.nome"
							placeholder="autocompleto digite o curso" />
						<form:label path="curso.id">ID</form:label>
						<form:input id="idCurso" class="form-control" path="curso.id"
							readonly="true" />
						<form:label path="telefone">Telefone</form:label>
						<form:input autocomplete="" class="form-control" path="telefone"
							placeholder="telefone" />
						<form:label path="email">Email</form:label>
						<form:input class="form-control" path="email" placeholder="email" />
						<button type="submit" class="btn btn-warning"
							style="margin-top: 9%; margin-left: 5%;">Atualizar</button>

					</div>
				</div>
			</form:form>

			<br>
			<c:if test="${!empty listaEmprestimo }">
				<h3 style="margin-left: 40%;">Historico dos Empréstimos de
					${aluno.nome}</h3>
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
							<th scope="col">Entregar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaEmprestimo}" var="emprestimo">
							<tr>
								<th scope="row"><a
									href="<c:url value='/emprestimo/${emprestimo.id}' />">${emprestimo.id}</a></th>
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
										href="<c:url value='/emprestimo/renovar/${emprestimo.id}' />">
											renovar </a></td>
								</c:if>
								<c:if test="${emprestimo.entregou == false}">
									<td><a style="color: red;"
										href="<c:url value='/emprestimo/entregar/${emprestimo.id}' />">
											entrega </a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<br>
			<c:if test="${!empty listaDivida }">
				<h3 style="margin-left: 40%;">Historico das Dividas de
					${aluno.nome}</h3>
				<table class="table text-center">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Código de Divida</th>
							<th scope="col">Título do Item</th>
							<th scope="col">Matrícula do aluno</th>
							<th scope="col">Nome do aluno</th>
							<th scope="col">Valor</th>
							<th scope="col">Pagou</th>
							<th scope="col">Pagamento</th>
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
								<c:if test="${divida.pago == false}">
									<td><a style="color: red;"
										href="<c:url value='/divida/pagar/${divida.id}' />"> pagar
									</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>

			<br>
			<c:if test="${!empty listaReserva }">
				<h3 style="margin-left: 40%;">Lista de Reservas de ${aluno.nome }</h3>
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
		$('#senha').hide()
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
			var ano = $("#ano").val();

			if (nome === '' || usuario === '' || endereco === '' || cpf === ''
					|| rg === '' || naturalidade === '' || telefone === ''
					|| email === '' || curso === '' || ano === '') {
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
