<%@ page import="Entidades.Cuentas" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>


<%
	@SuppressWarnings("unchecked")
    List<Cuentas> cuentas = (List<Cuentas>) session.getAttribute("cuentas");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/Estilo.css">
<title>Mis cuentas</title>
</head>
<body>
<jsp:include page="Nav.jsp" />
<jsp:include page="SubNav.jsp" />

<div class="container-cuentas">

<%
    if (cuentas != null) {%>
    	<h2>Cuentas</h2>
       <% for (Cuentas c : cuentas) {
%>


	<div class="Cuentas">
		<ul class="Cuentas-items">
		<li>
				<%
                    if (c.getTipocuenta() == 1) {
                        out.print("Caja de ahorro en pesos");
                    } else if (c.getTipocuenta() == 2) {
                        out.print("Cuenta corriente en pesos");
                    } else {
                        out.print("Tipo desconocido");
                    }
                %>
		</li>
		<li><%= c.getNumero() %></li>
            <li>Moneda: Pesos</li>
            <li>$<%= c.getSaldo() %></li>
            <li>CBU: <%= c.getCbu() %></li>
		</ul>
		
		<a href="MovimientosCliente.jsp?idCuenta=<%= c.getIdCuenta() %>">Ver movimientos</a>
	</div>
<%
        }
    }
%>


</div>
</body>
</html>