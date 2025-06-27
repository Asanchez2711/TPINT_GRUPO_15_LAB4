<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Administración de Préstamos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/Estilo.css">
</head>
<body>
    <jsp:include page="NavAdministrador.jsp" />
    <jsp:include page="SubNavAdministrador.jsp" />

    <div class="container my-5">
        <h2 class="mb-4">Gestión de Préstamos Solicitados</h2>

        <div class="table-responsive">
            <table class="table table-bordered table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th>Cliente</th>
                        <th>Cuenta destino</th>
                        <th>Monto solicitado</th>
                        <th>Cuotas</th>
                        <th>Fecha de solicitud</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Ejemplo visual de fila -->
                    <tr>
                        <td>Juan Pérez</td>
                        <td>123456789</td>
                        <td>$ 80.000</td>
                        <td>12</td>
                        <td>2025-06-10</td>
                        <td><span class="badge bg-warning text-dark">Pendiente</span></td>
                        <td class="d-flex gap-2">
                            <button class="btn btn-success btn-sm">Autorizar</button>
                            <button class="btn btn-danger btn-sm">Rechazar</button>
                             <a href="FichaCliente.jsp?idCliente=1" class="btn btn-info btn-sm">Ver Ficha</a>
                            
                        </td>
                    </tr>
                    <!-- Acá se cargarían más préstamos dinámicamente -->
                </tbody>
            </table>
        </div>

        <div class="alert alert-info mt-4" role="alert">
            Recordá revisar los datos del cliente antes de aprobar el préstamo.
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
