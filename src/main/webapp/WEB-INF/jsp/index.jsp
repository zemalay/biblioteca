<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>' />
<link rel="stylesheet" href='<c:url value="/resources/izitoast/css/iziToast.min.css"/>' />
<link rel="stylesheet"
	href='<c:url value="/resources/css/style.css"/>' />
<script
	src="<c:url value="/resources/javascript/jquery/jquery-3.3.1.min.js" />"></script>
<script src="<c:url value="/resources/javascript/popper.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<title>Sistema BibLioteca</title>
</head>
<body>
	<nav class="navbar navbar" style="background-color:darkcyan"> 
	
	<form:form id="funcionarioForm" modelAttribute="funcionario" method="POST" action="/biblioteca/funcionario/auth" class="form-inline">
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
		<div class="form-group">
        		<a id="linkAluno" href="#">Aluno?</a>
      	</div>
	</form:form>
	
	<form:form id="alunoForm" modelAttribute="aluno" method="POST" action="/biblioteca/aluno/auth" class="form-inline">
		<form:label path="matricula" class="sr-only">Matricula</form:label>
		<form:input class="form-control mb-2 mr-sm-2" placeholder="matricula"
			path="matricula" />
		<form:label class="sr-only" path="senha">Senha</form:label>
		<div class="input-group mb-2 mr-sm-2">
			<div class="input-group-prepend"></div>
			<form:password class="form-control" placeholder="senha" path="senha" />
		</div>
		<div>
			<input type="submit" class="btn btn-dark mb-2" value="Submit">
		</div>
		<div class="form-group">
        		<a id="linkFuncionario" href="#">Funcionario?</a>
      	</div>
	</form:form>

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
	</div>
	<script type="text/javascript" src="<c:url value="/resources/izitoast/js/iziToast.min.js" />"></script>
	<script type="text/javascript">
	var mensagem = '${mensagem}';
	console.log("mensagem :" + mensagem);
	if ( mensagem != "") {
	      iziToast.show({
	        title: 'Erro',
	        message: mensagem,
	        color: 'red',
	        timeout: false,
	        position: 'topRight'
	      });
	    }
	</script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/action.js" />"></script>

</body>
</html>