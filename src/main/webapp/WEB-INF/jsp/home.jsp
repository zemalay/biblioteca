<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<link rel="stylesheet" href='<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>' />
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<title>Insert title here</title>
</head>
<body>
	<c:if test="${ funcionario.id > 0 }">
		<h3>${funcionario.id}</h3>
		<h3>${funcionario.usuario}</h3>
	</c:if>

	<script type="text/javascript" src="/resources/javascript/action.js"></script>
</body>
</html>
