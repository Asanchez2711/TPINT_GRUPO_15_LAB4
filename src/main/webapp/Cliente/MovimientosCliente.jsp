<%@ page import="Entidades.Movimiento" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.DaoCliente" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String idCuentaStr = request.getParameter("idCuenta");
    int idCuenta = 0;
    List<Movimiento> movimientos = null;

    if (idCuentaStr != null) {
        try {
            idCuenta = Integer.parseInt(idCuentaStr);
            DaoCliente dao = new DaoCliente();
            movimientos = dao.obtenerMovimientosPorCuenta(idCuenta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/Estilo.css">
<title>Mis Movimientos</title>
</head>
<body>
<jsp:include page="Nav.jsp" />
<jsp:include page="SubNav.jsp" />
<div class="Container-movimientos">
<h2>Movimientos</h2>
<table class="tabla-movimientos">
 <%
    if (movimientos != null && !movimientos.isEmpty()) {
        for (Movimiento m : movimientos) {
%>
    <tr>
        <td><%= m.getFecha() %></td>
        <td><%= m.getDescripcion() %></td>
        <td>
            <%
                switch (m.getTipoMovimiento()) {
                    case 1: out.print("Alta de cuenta"); break;
                    case 2: out.print("Alta de préstamo"); break;
                    case 3: out.print("Pago de préstamo"); break;
                    case 4: out.print("Transferencia"); break;
                    default: out.print("Desconocido");
                }
            %>
        </td>
        <td>$<%= m.getMonto() %></td>
    </tr>
<%
        }
    } else {
%>
    <tr><td colspan="4">No hay movimientos para mostrar.</td></tr>
<%
    }
%>
  </table>
  <a href="Cuentas.jsp" class="btn-volver">Volver</a>
 </div>
</body>
</html>