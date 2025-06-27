package Serverlet;


import Entidades.Usuario;
import Negocio.NegocioUsuario;
import NegocioImp.NegocioUsuarioImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ListarUsuarios")
public class ServletUsuario extends HttpServlet {
    private NegocioUsuario negocioUsuario = new NegocioUsuarioImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Usuario> usuarios = negocioUsuario.listarUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("usuarios.jsp").forward(request, response);
    }
}
