<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String idCliente = request.getParameter("idCliente");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Ficha del Cliente</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/Estilo.css">
</head>
<body>

    <jsp:include page="NavAdministrador.jsp" />
    <jsp:include page="SubNavAdministrador.jsp" />

    <div class="container mt-4">
        <h2 class="text-center mb-4">Ficha del Cliente</h2>

        <%
            if (idCliente != null && !idCliente.isEmpty()) {
        %>
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title mb-3">ID Cliente: <%= idCliente %></h5>

                            <div class="mb-3">
                                <strong>Nombre:</strong> Juan
                            </div>
                            <div class="mb-3">
                                <strong>Apellido:</strong> Pérez
                            </div>
                            <div class="mb-3">
                                <strong>Estado:</strong>
                                <span class="badge bg-success">Activo</span>
                            </div>
                            <div class="mb-3">
                                <strong>Cuenta:</strong> 1234567890
                            </div>
                            <div class="mb-3">
                                <strong>DNI:</strong> 30.123.456
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <%
            } else {
        %>
            <div class="alert alert-warning text-center" role="alert">
                No se recibió ningún ID de cliente.
            </div>
        <%
            }
        %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
