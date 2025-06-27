<%@ page import="java.util.List" %>
<%@ page import="Entidades.Cuentas" %>
<%
	@SuppressWarnings("unchecked")
    List<Cuentas> cuentas = (List<Cuentas>) session.getAttribute("cuentas");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/Estilo.css">
    <title>Transferencias</title>
</head>
<body>
<jsp:include page="Nav.jsp" />
<jsp:include page="SubNav.jsp" />

<input type="hidden" name="Cuenta" value="${cuenta.id}" />

<div class="Container-transferencias">
<h2>Transferencia a nuevo destinatario</h2>

<%
    if (cuentas != null && !cuentas.isEmpty()) {
%>
<form class="FormularioTransferencia" method="post" action="${pageContext.request.contextPath}/ServletCliente">
    <div class="FormItem">
        <label for="Cuenta">Seleccionar cuenta origen</label>
        <select name="Cuenta" id="Cuenta">
            <%
                for (Cuentas c : cuentas) {
                    String tipo = "";
                    if (c.getTipocuenta() == 1) tipo = "Caja de ahorro en pesos";
                    else if (c.getTipocuenta() == 2) tipo = "Cuenta corriente en pesos";
                    else tipo = "Tipo desconocido";
            %>
                <option value="<%= c.getIdCuenta() %>"><%= tipo %> - N° <%= c.getNumero() %></option>
            <%
                }
            %>
        </select>
    </div>

    <div class="FormItem">
        <label for="Monto">Monto a transferir</label>
        <input type="text" name="txtMonto" id="txtMonto" required/>
    </div>

    <div class="FormItem">
        <label for="CBU">CBU destino</label>
        <input type="text" name="txtCbu" id="txtCbu" required/>
    </div>

    <div class="FormItem">
        <label for="Descripcion">Descripción</label>
        <input type="text" name="txtDescripcion" id="txtDescripcion"/>
    </div>

    <div class="FormItem">
        <input type="submit" name="btnTransferir" value="Transferir" class="btnTransferir"/>
    </div>

    <% String error = (String) request.getAttribute("error");
        if(error != null) {
    %>
        <p class="error"><%= error %></p>
    <% } %>
     <% String mensaje = (String) request.getAttribute("mensaje");
        if(mensaje != null) {
    %>
        <p class="mensaje"><%= mensaje %></p>
    <% } %>
</form>
<%
    } else {
%>
    <p>No hay cuentas disponibles para realizar transferencias.</p>
<%
    }
%>
</div>
</body>
</html>
