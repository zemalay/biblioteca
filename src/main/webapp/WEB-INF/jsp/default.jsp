<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href='<c:url value="/resources/css/style.css"/>' />
<link rel="stylesheet"
	href='<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>' />
<script src="<c:url value="/resources/javascript/jquery/jquery-3.3.1.min.js" />"></script>
<script src="<c:url value="/resources/javascript/popper.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="wrapper">

		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a href="#"> Biblioteca-UFAB </a></li>
				<li><a href="#">Cadastro</a></li>
				<li>
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Item da
							Biblioteca</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="#">Listar</a> <a
								class="dropdown-item" href="#">Cadastrar</a> <a
								class="dropdown-item" href="#">Something else here</a>
						</div>
					</div>
				<li>
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							Aluno(a)
						</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="#">Listar</a> <a
								class="dropdown-item" href="#">Cadastrar</a> <a
								class="dropdown-item" href="#">S</a>
						</div>
					</div>
				</li>
				<li>
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							Funcionário(a)</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="#">Listar</a> <a
								class="dropdown-item" href="#">Cadastrar</a> <a
								class="dropdown-item" href="#"></a>
						</div>
					</div>
				</li>
				<li>
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Empréstimo</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="#">Listar</a> <a
								class="dropdown-item" href="#">Cadastrar</a> <a
								class="dropdown-item" href="#"></a>
						</div>
					</div>
				</li>
				<li><a href="#">Sobre</a></li>

			</ul>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<nav class="navbar"> <a class="navbar-brand btn"
					href="#menu-toggle" id="menu-toggle"> <span
					class="glyphicon glyphicon-print"></span>Menu
				</a>
				<form class="form-inline">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Procurar" aria-label="Search">
					<button class="btn btn-warning my-2 my-sm-0" type="submit">Procurar</button>
				</form>
				</nav>

			</div>
		</div>
		<div class="container">
			<h2>Alunos cadastrados na biblioteca-UFAB</h2>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Matrícula</th>
						<th>Curso</th>
						<th>Item emprestado</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>

					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>

				</tbody>
			</table>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->
	<script src="<c:url value="/resources/javascript/action.js" />"></script>
</body>
</html>
