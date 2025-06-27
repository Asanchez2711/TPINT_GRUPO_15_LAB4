<%@ page import="Entidades.Cliente" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/Estilo.css">
</head>

<body>


	<header class="Nav-Bar">
		<ul class="Li-item">
			<li><a href="<%=request.getContextPath()%>/Cliente/MenuCliente.jsp">BANCO</a></li>
		</ul>
<div class="Login">
        <a href="<%=request.getContextPath()%>/Cliente/InfoPersonal.jsp">${cliente.nombre}  </a>
        <a href="${pageContext.request.contextPath}/CerrarSesion">Cerrar sesión</a>
    </div>
       
    	
	</header>
	
</body>
</html>