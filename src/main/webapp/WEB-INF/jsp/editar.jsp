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
	<div class="p-3">

		<h1>Editar usuario</h1>
		<c:if test="${mensaje != null ? true : false}">
			<div class="alert alert-secondary  alert-dismissible fade show"
				role="alert">${mensaje}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<form action="editar" method="post">
			<input type="hidden" name="idUsuario" value="${VO.getIdUsuario()}" />
			<table>

				<tr>
					<td class="p-2"><label for="nombre">Nombre:</label></td>
					<td><input class="form-control" type="text" name="nombre"
						placeholder="Nombre" value="${VO.getNombre()}" /></td>
				</tr>
				<tr>
					<td class="p-2"><label for="clave">Clave:</label></td>
					<td><input class="form-control" type="password"
						placeholder="Contraseña" name="clave" value="${VO.getClave()}" /></td>
				</tr>
				<tr>
					<td class="p-2"><label for="rut">Rut:</label></td>
					<td><input class="form-control" type="number"
						placeholder="Rut" name="rut" maxlength="99999999"
						value="${VO.getRut()}" /></td>
					<td>-</td>
					<td><input type="text" size="1" step="1" class="form-control" name="dv"
						value="${VO.getDv()}" maxlength="1" /></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit"
						class="btn m-2 btn-success" value="Guardar" /></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
