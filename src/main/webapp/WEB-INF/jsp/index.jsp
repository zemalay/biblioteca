<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href='<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>' />
<link rel="stylesheet"
	href='<c:url value="/resources/css/style.css"/>' />
<script
	src="<c:url value="/resources/javascript/jquery/jquery-3.3.1.min.js" />"></script>
<script src="<c:url value="/resources/javascript/popper.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<title>HelloWorld page</title>
</head>
<body>
	<nav class="navbar navbar" style="background-color:darkcyan"> 
	<form:form method="POST" action="/biblioteca/auth" modelAttribute="funcionario" class="form-inline"
		style="margin-left: 40%;">
		<form:label path="usuario" class="sr-only">Usuario</form:label>
		<form:input class="form-control mb-2 mr-sm-2" placeholder="usuario"
			path="usuario" />
		<form:label class="sr-only" path="senha">Senha</form:label>
		<div class="input-group mb-2 mr-sm-2">
			<div class="input-group-prepend"></div>
			<form:password class="form-control" placeholder="senha" path="senha" />
		</div>
		<div>
			<input type="submit" class="btn btn-dark mb-2" value="Submit">
		</div>
	</form:form>

	<%-- <form class="form-inline" style="margin-left: 40%;">
		<label class="sr-only" for="inlineFormInputName2">Name</label> <input
			type="text" class="form-control mb-2 mr-sm-2"
			id="inlineFormInputName2" placeholder="Login"> <label
			class="sr-only" for="inlineFormInputGroupUsername2">Senha</label>
		<div class="input-group mb-2 mr-sm-2">
			<div class="input-group-prepend"></div>
			<input type="password" class="form-control"
				id="inlineFormInputGroupUsername2" placeholder="Senha">
		</div>
		<div>
			<button type="submit" class="btn btn-dark mb-2">Entrar</button>
		</div> --%>

		<!-- <div class="form-check mb-2 mr-sm-2">
			<div class="dropdown-divider"></div>
			<a class="dropdown-item" href="#" style="color: white">Não
				possui cadastro? Cadastre-se</a>
		</div> -->

	</nav>
	<div class="row">
		<div class="col-8">
			<div class="card"
				style="width: 18rem; margin-top: 10%; margin-left: 30%;">
				<img class="card-img-top" src="<c:url value="/resources/imagens/livro.png" />" alt="Card image cap">
				<div class="card-body">
					<h2 class="card-text">Biblioteca-UFAB</h2>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<form class="form-inline" id="search">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Itens do acervo" aria-label="Procurar">
				<button class="btn btn-dark my-2 my-sm-0" type="submit">Procurar</button>
			</form>
		</div>
	</div>
	<script type="text/javascript"
		src="<c:url value="/resources/javascript/action.js" />"></script>

</body>
</html>