<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="sidebar-wrapper" style="background-color: darkcyan;">
	<ul class="sidebar-nav">
		<li class="sidebar-brand"><a href="<c:url value='/aluno/home' />"
			style="color: white;"> Biblioteca-UFAB </a></li>

		<li><a href="<c:url value='/aluno/perfil' />"
			style="color: white;">Perfil</a></li>
		<li><a href="<c:url value='/aluno/itens' />"
			style="color: white;">Itens da Biblioteca</a></li>
		<li><a href="<c:url value='/aluno/reserva/form' />"
			style="color: white;">Reserva Item</a></li>
			<li><a href="<c:url value='/sair' />"
			style="color: white;">Sair</a></li>
		<li><a href="#" style="color: white;">Sobre</a></li>

	</ul>
</div>
