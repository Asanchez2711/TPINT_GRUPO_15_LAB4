package NegocioImp;

import Dao.DaoUsuario;
import Entidades.Usuario;
import java.util.List;
import Negocio.NegocioUsuario;

public class NegocioUsuarioImp implements NegocioUsuario {
    private DaoUsuario daoUsuario = new DaoUsuario();

    @Override
    public List<Usuario> listarUsuarios() {
        return daoUsuario.listarUsuarios();
    }
}

