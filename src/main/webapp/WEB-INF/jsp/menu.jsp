<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="sidebar-wrapper" style="background-color: darkcyan;">
	<ul class="sidebar-nav">
		<li class="sidebar-brand"><a href="<c:url value='/home' />"
			style="color: white;"> Biblioteca-UFAB </a></li>

		<li>
			<div class="dropdown">
				<button class="btn btn-dark dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Item da Biblioteca</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="<c:url value='/itens' />">Listar</a>
					<c:if test="${funcionario.tipoFunc == 'ADMINISTRADOR' }">
						<a class="dropdown-item" href="<c:url value='/item/form' />">Cadastrar</a>
					</c:if>


				</div>
			</div>
		</li>
		<li>
			<div class="dropdown">
				<button class="btn btn-dark dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Aluno(a)</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="<c:url value='/alunos' />">Listar</a>
					<a class="dropdown-item" href="<c:url value='/aluno/form' />">Cadastrar</a>

				</div>
			</div>
		</li>

		<li>
			<div class="dropdown">
				<button class="btn btn-dark dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Curso</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="<c:url value='/cursos' />">Listar</a>
					<c:if test="${funcionario.tipoFunc == 'ADMINISTRADOR' }">
						<a class="dropdown-item" href="<c:url value='/curso/form' />">Cadastrar</a>
					</c:if>

				</div>
			</div>
		</li>

		<li>
			<div class="dropdown">
				<button class="btn btn-dark dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Funcionário(a)</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="<c:url value='/funcionarios' />">Listar</a>
					<c:if test="${funcionario.tipoFunc == 'ADMINISTRADOR' }">
						<a class="dropdown-item"
							href="<c:url value='/funcionario/form' />">Cadastrar</a>
					</c:if>
				</div>
			</div>
		</li>
		<li>
			<div class="dropdown">
				<button class="btn btn-dark dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Empréstimo</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="#">Listar</a> <a
						class="dropdown-item" href="#">Cadastrar</a> <a
						class="dropdown-item" href="#"></a>
				</div>
			</div>
			<div class="dropdown">
				<button class="btn btn-dark dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Reservar</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="#">Listar</a> <a
						class="dropdown-item" href="#">Cadastrar</a> <a
						class="dropdown-item" href="#"></a>
				</div>
			</div>
		</li>
		<li><a href="#" style="color: white;">Dívida</a></li>
		<c:if test="${funcionario.tipoFunc == 'ADMINISTRADOR' }">
			<li><a href="#" style="color: white;">Configuração</a></li>
		</c:if>

		<li><a href="#" style="color: white;">Sobre</a></li>

	</ul>
</div>
