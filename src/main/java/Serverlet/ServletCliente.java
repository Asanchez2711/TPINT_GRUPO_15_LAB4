package Serverlet;

import java.io.IOException;

import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Entidades.Cliente;
import Entidades.Usuario;
import Negocio.NegocioCliente;
import NegocioImp.NegocioClienteImp;
import Otros.enmTipos;


public class ServletCliente extends HttpServlet {
    private static final long serialVersionUID = 1L; // Añadir un serialVersionUID

    private NegocioCliente negocio;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if(request.getParameter("btnTransferir")!=null) {
			eventobtnTransferir(request, response);
			return;
		}
        // 1. Obtener los parámetros del formulario
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String dni = request.getParameter("dni");
        String cuil = request.getParameter("cuil");
        String apellido = request.getParameter("apellido");
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo"); 
        String nacionalidad = request.getParameter("nacionalidad");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        String provincia = request.getParameter("provincia");
        String localidad = request.getParameter("localidad");
        String direccion = request.getParameter("direccion");
        String mail = request.getParameter("mail");
        String telefono = request.getParameter("telefono");
        // Para el campo 'activo', si no hay un checkbox, asumimos true por defecto.
        // Si hubiera un checkbox, se leería algo como request.getParameter("activo") != null;

        // Log de los parámetros recibidos para depuración
        System.out.println("--- Parámetros recibidos en ServletCliente ---");
        System.out.println("Usuario: " + usuario);
        System.out.println("Password: " + password);
        System.out.println("DNI: " + dni);
        System.out.println("CUIL: " + cuil);
        System.out.println("Apellido: " + apellido);
        System.out.println("Nombre: " + nombre);
        System.out.println("Sexo: " + sexo);
        System.out.println("Nacionalidad: " + nacionalidad);
        System.out.println("Fecha de Nacimiento (String): " + fechaNacimientoStr);
        System.out.println("Provincia: " + provincia);
        System.out.println("Localidad: " + localidad);
        System.out.println("Dirección: " + direccion);
        System.out.println("Mail: " + mail);
        System.out.println("Teléfono: " + telefono);
        System.out.println("----------------------------------------------");

        Cliente cliente = new Cliente();
        try {
        	Usuario usr = new Usuario(0,"","",enmTipos.UsrCliente.valor,true); 
        	cliente.setUsuario(usr);
            cliente.setDni(dni);
            cliente.setCuil(cuil);
            cliente.setApellido(apellido);
            cliente.setNombre(nombre);
            cliente.setSexo(sexo);
            cliente.setNacionalidad(nacionalidad);
            cliente.setFechaAlta(LocalDate.now()); 

            if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
                cliente.setFechaNacimiento(LocalDate.parse(fechaNacimientoStr));
            } else {
                System.err.println("Error: Fecha de Nacimiento es nula o vacía.");
                response.sendRedirect(request.getContextPath() + "/Administrador/Clientes.jsp?mensaje=errorFecha");
            }

            cliente.setProvincia(provincia);
            cliente.setLocalidad(localidad);
            cliente.setDireccion(direccion);
            cliente.setMail(mail);
            cliente.setTelefono(telefono);
            cliente.setActivo(true); 

            negocio = new NegocioClienteImp();
            boolean exito = negocio.registrarCliente(cliente);

            if (exito) {
                response.sendRedirect(request.getContextPath() + "/Administrador/Clientes.jsp?mensaje=ok");
            } else {
                response.sendRedirect(request.getContextPath() + "/Administrador/Clientes.jsp?mensaje=error");
            }
        } catch (Exception e) {
            System.err.println("Error al procesar los datos del cliente en ServletCliente:");
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/Administrador/Clientes.jsp?mensaje=errorProcesamiento");
        }
    }
    
    public void eventobtnTransferir( HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String montoStr = request.getParameter ("txtMonto");
		String cbuStr = request.getParameter("txtCbu");
		String cuentaEmisorStr = request.getParameter("Cuenta");
		String descripcion = request.getParameter("txtDescripcion");
		
		if(montoStr == null || montoStr.trim().isEmpty() || cbuStr == null 
		|| cbuStr.trim().isEmpty() || cuentaEmisorStr == null || cuentaEmisorStr.trim().isEmpty()) {
			
			
			request.setAttribute("error", "Todos los campos son obligatorios. ");
			request.getRequestDispatcher("Cliente/Transferencias.jsp").forward(request, response);
			return;
		}
		
		try {
			float monto = Float.parseFloat(montoStr);
			int cuentaEmisorID = Integer.parseInt(cuentaEmisorStr);
			
			if(monto <=0) {
				request.setAttribute("error", "El monto debe ser mayor a cero. ");
				request.getRequestDispatcher("Cliente/Transferencias.jsp").forward(request, response);
				return;
			}
	        NegocioCliente negocio = new NegocioClienteImp();
	        int cuentaReceptorID = negocio.obtenerIdCuentaPorCBU(cbuStr);
	        
	        if(cuentaReceptorID == 0) {
	        	request.setAttribute("error", "CBU no válido o cuenta inexistente.");
	            request.getRequestDispatcher("Cliente/Transferencias.jsp").forward(request, response);
	            return;
	        }
	        
	        int numeroMovimiento = 9; 
	        java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
	        boolean pudo = negocio.registrarTransferencia(cuentaEmisorID, cuentaReceptorID, numeroMovimiento, monto, descripcion, fechaActual);
	        if(pudo) {
	            request.setAttribute("mensaje", "Transferencia realizada con éxito.");
	            HttpSession session = request.getSession(false);
	            if (session != null) {
	                Integer clienteId = (Integer) session.getAttribute("clienteId"); 
	                if (clienteId != null) {
	                    java.util.List<?> cuentas = negocio.obtenerCuentasPorCliente(clienteId);
	                    session.setAttribute("cuentas", cuentas);
	                    request.setAttribute("cuentas", cuentas); // Actualiza también el request
	                }
	            }
	        }
	        
	        else {request.setAttribute("error", "Error al realizar transferencia, revisar limite de cuenta.");}
	        
	        request.getRequestDispatcher("Cliente/Transferencias.jsp").forward(request, response);
	        
	        
		}
		catch(NumberFormatException e) {
			request.setAttribute("error", "Formato inválido en alguno de los campos. ");
			request.getRequestDispatcher("Cliente/Transferencias.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Ocurrió un error inesperado.");
	        request.getRequestDispatcher("Cliente/Transferencias.jsp");
		}
	}
    
}