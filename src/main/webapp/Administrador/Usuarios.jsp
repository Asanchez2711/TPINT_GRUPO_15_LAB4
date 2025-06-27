<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Página Usuario</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/Estilo.css">
<%@ page import="java.util.*, Entidades.Usuario"%>
<%
List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
%>
</head>
<body>

	<jsp:include page="NavAdministrador.jsp" />
	<jsp:include page="SubNavAdministrador.jsp" />

	<div class="container mt-5">
		<h2 class="mb-4 text-center">Listado de Usuarios</h2>

		<table class="table table-bordered table-hover text-center">
			<thead class="table-dark">
				<tr>
					<th>ID</th>
					<th>Nombre de Usuario</th>
					<th>Email</th>
					<th>Rol</th>
					<th>Estado</th>
					<th>Acciones</th>
				</tr>
			</thead>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>

					<button type="button" class="btn btn-warning"
						data-bs-toggle="modal" data-bs-target="#miModal2">Editar</button>

					<div class="modal fade" id="miModal2" tabindex="-1"
						aria-labelledby="ModalLabel2" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="ModalLabel2">Editar</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Cerrar"></button>
								</div>
								<div class="modal-body">Hola probando</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-warning"
										data-bs-dismiss="modal">Editar</button>
								</div>
							</div>
						</div>
					</div>

					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#miModal3">Dar Baja</button>

					<div class="modal fade" id="miModal3" tabindex="-1"
						aria-labelledby="miModalLabel3" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="miModalLabel3">Dar de Baja</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Cerrar"></button>
								</div>
								<div class="modal-body">¿Estas Seguro que quieres dar de
									baja?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-danger"
										data-bs-dismiss="modal">Baja</button>
								</div>
							</div>
						</div>
					</div>
				</td>

			</tr>

			<tbody>
				<%
				if (usuarios != null && !usuarios.isEmpty()) {
					for (Usuario u : usuarios) {
				%>
				<tr>
					<td><%=u.getId()%></td>
					<td><%=u.getUsuario()%></td>
					<td>email@demo.com</td>
					<!-- Cambiar si tenés campo email -->
					<td><%=u.getTipoUsuario() == 1 ? "Administrador" : "Usuario"%></td>
					<td><%=u.isActivoOK() ? "Activo" : "Inactivo"%></td>
					<td>
						<button class="btn btn-warning">Editar</button>
						<button class="btn btn-danger">Dar Baja</button>
					</td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="6">No hay usuarios para mostrar.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>

		<button type="button" class="btn btn-success" data-bs-toggle="modal"
			data-bs-target="#miModal">Agregar</button>


		<div class="modal fade" id="miModal" tabindex="-1"
			aria-labelledby="miModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="miModalLabel">Agregar</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Cerrar"></button>
					</div>
					<div class="modal-body">Hola probando</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
