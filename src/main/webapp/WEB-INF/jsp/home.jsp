<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.latam.JPAcrud.modelo.Usuario"%>
<%@page import="com.latam.JPAcrud.vo.UsuarioVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="charset=ISO-8859-1">
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.3.0/css/bootstrap.min.css" />
<title>JSP CRUD</title>
</head>
<body>
	<!-- Inicio Header -->
	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="#">JSP CRUD</a>
		<div class="navbar">
			<div class="navbar-nav">
				<form action="handleLogout" class="form-inline" method="POST">
					<a class="nav-item nav-link disabled mr-sm-2" href="#"
						tabindex="-1" aria-disabled="true">${usuarioConectado}</a> <input
						type="submit" class="btn btn-outline-danger my-2 my-sm-0"
						name="btnEnviar" value="Cerrar sesión">
				</form>
			</div>
		</div>
	</nav>
	<!-- Fin Header -->

	<!-- Inicio Contenido -->
	<div class="p-3">

		<!-- Inicio Mensajes -->
		<c:if test="${mensaje != null ? true : false}">
			<div class="alert alert-secondary alert-dismissible fade show"
				role="alert">${mensaje}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<!-- Fin Mensajes -->

		<!-- Botón agregar usuario -->
		<a href="agregarForm" class="btn m-2 btn-success">Agregar usuario</a>

			<ul class="pagination pagination-lg justify-content-center">
				<c:forEach items="${paginas}" var="pagina">
					<li class="page-item ${paginaActual == pagina ? 'disabled' : ''}"><a class="page-link" href="home?p=${pagina}" tabindex="-1">${pagina}</a></li>
				</c:forEach>
			</ul>

		<!-- Inicio Tabla -->
		<table border="1" class="table table-hover">
			<thead class="thead-dark">
				<tr>
					<th scope="col">IdUsuario</th>
					<th scope="col">Nombre</th>
					<th scope="col">Clave</th>
					<th scope="col">Rut</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${VO.usuarios}" var="u">
					<tr>
						<td>${u.getIdUsuario()}</td>
						<td>${u.getNombre()}</td>
						<td>${u.getClave()}</td>
						<td>${u.getRut()}-${u.getDv()}</td>
						<td>
							<a href="editarForm?idUsuario=${u.getIdUsuario()}" class="btn btn-primary btn-sm">Editar</a>
							<a href="eliminar?idUsuario=${u.getIdUsuario()}" class="btn btn-danger btn-sm">Eliminar</a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<!-- Fin tabla -->
	</div>

	<!-- Fin Contenido -->
</body>
</html>
