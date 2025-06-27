package Serverlet;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.DaoCliente;
import Entidades.Cliente;
import Entidades.Cuentas;
import Entidades.Usuario;
import Negocio.NegocioCliente;
import NegocioImp.NegocioClienteImp;

/**
 * Servlet implementation class ServerletHTML
 */


public class ServerletHTML extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerletHTML() { 
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("btnSolicitar")!=null) {
			eventobtnSolicitar(request, response);
			
			return;
		}
		
		
		if(request.getParameter("btnIniciar")!=null) {
			eventobtnIniciar(request, response);
			return;
		}
		
		
	}
	
	
	public void eventobtnSolicitar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String montoStr = request.getParameter("txtMonto");
	    String plazoStr = request.getParameter("Plazo");
	    String cuentaStr = request.getParameter("Cuenta");
	   
	    if(montoStr == null || montoStr.trim().isEmpty() || 
	       plazoStr == null || plazoStr.trim().isEmpty() || 
	       cuentaStr == null || cuentaStr.trim().isEmpty()) {
	        request.setAttribute("error", "Todos los campos son obligatorios.");
	        request.getRequestDispatcher("Cliente/SolicitarPrestamo.jsp").forward(request, response);
	        return;
	    }
	    
	    try {
	        float monto = Float.parseFloat(montoStr);
	       
	       
	        if(monto <= 0 || monto > 1000000) {
	            request.setAttribute("error", "El monto debe estar entre $1 y $1,000,000");
	            request.getRequestDispatcher("Cliente/SolicitarPrestamo.jsp").forward(request, response);
	            return;
	        }
	        
	       
	        response.sendRedirect(request.getContextPath() + "/Cliente/Prestamos.jsp");
	        
	    } catch(NumberFormatException e) {
	        request.setAttribute("error", "Formato inválido en alguno de los campos.");
	        request.getRequestDispatcher("Cliente/SolicitarPrestamo.jsp").forward(request, response);
	    }
	}
	
	public void eventobtnIniciar( HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String usuarioStr = request.getParameter("txtUsuario");
	    String claveStr = request.getParameter("txtClave");
	    System.out.println("Usuario recibido: '" + usuarioStr + "'");
	    System.out.println("Clave recibida: '" + claveStr + "'");
		if(usuarioStr == null || usuarioStr.trim().isEmpty() || claveStr == null || claveStr.trim().isEmpty()) {
			request.setAttribute("error", "Todos los campos son obligatorios.");
	        request.getRequestDispatcher("/Login.jsp").forward(request, response);
	        return;
		}
		
		
		 
		try {
			
		NegocioCliente a = new NegocioClienteImp(); 
		Usuario usuario = a.obtieneUsuario(usuarioStr, claveStr);
		
		if(usuario != null && usuario.isActivoOK()) {
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario);
			if(usuario.getTipoUsuario() == 2) {
				
				DaoCliente daoCliente = new DaoCliente();
				
				Cliente cliente = daoCliente.obtenerClientePorUsuario(usuario.getId());
				System.out.println("ID del cliente: " + cliente.getIdCliente());
				List<Cuentas> cuentas = daoCliente.obtenerCuentasPorCliente(cliente.getIdCliente());
				System.out.println("Cliente obtenido: " + (cliente != null ? cliente.getNombre() : "NULL"));
			
				session.setAttribute("cliente", cliente);
				session.setAttribute("cuentas", cuentas);
				request.getRequestDispatcher("/Cliente/MenuCliente.jsp").forward(request, response);
				
			}else if (usuario.getTipoUsuario()==1) {
				request.getRequestDispatcher("/Administrador/MenuAdministrador.jsp").forward(request, response);
			}
			else {
				request.setAttribute("error", "Usuario desconocido.");
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("error", "Usuario o clave incorrectos.");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);

		}
		}
		
		catch(Exception e) {
			 e.printStackTrace();
			 request.setAttribute("error", "Error interno del sistema.");
			 request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}

}
