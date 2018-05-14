<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='<c:url value="/resources/css/style.css"/>' />
<link rel="stylesheet"
	href='<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />'
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" />"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<title>HelloWorld page</title>
</head>
<body>

	<div class="row">
		<div class="col-4">
			<c:if test="${!empty mensagem }">
				<div class="alert alert-primary" role="alert">${mensagem}</div>
			</c:if>
			<form:form method="POST" action="/biblioteca/auth"
				modelAttribute="funcionario">

				<div class="form-group">
					<form:label path="usuario">Usuario</form:label>
					<form:input class="form-control" placeholder="usuario"
						path="usuario" />
				</div>
				<div class="form-group">
					<form:label path="senha">Senha</form:label>
					<form:password class="form-control" placeholder="senha"
						path="senha" />
				</div>
				<br>
				<input type="submit" class="btn btn-primary" value="Submit">
			</form:form>
		</div>
	</div>



</body>
</html>