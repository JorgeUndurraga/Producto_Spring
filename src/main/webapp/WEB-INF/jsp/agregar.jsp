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

	<!-- Inicio Contenido -->
	<div class="p-3">

		<h1>Agregar usuario</h1>
		<c:if test="${mensaje != null ? true : false}">
			<div class="alert alert-secondary  alert-dismissible fade show"
				role="alert">${mensaje}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<form action="agregar" method="post">
			<table>
				<tr>
					<td class="p-2"><label for="nombre">Nombre:</label></td>
					<td><input class="form-control" type="text" name="nombre"
						placeholder="Nombre" /></td>
				</tr>
				<tr>
					<td class="p-2"><label for="clave">Clave:</label></td>
					<td><input class="form-control" type="password"
						placeholder="Contraseña" name="clave" /></td>
				</tr>
				<tr>

					<td class="p-2"><label for="rut">Rut:</label></td>
					<td><input class="form-control" type="number"
						placeholder="Rut" name="rut" /></td>
					<td>-</td>
					<td><input class="form-control" type="text" placeholder="DV"
						step="1" size="1" maxlength="1" name="dv" /></td>

				</tr>

				<tr>
					<td colspan="2"><input type="submit"
						class="btn m-2 btn-success" value="Agregar" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
