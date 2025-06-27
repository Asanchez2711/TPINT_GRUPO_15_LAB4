package NegocioImp;


import java.util.List;

import Dao.DaoCliente;
import Entidades.Cliente;
import Entidades.Cuentas;
import Entidades.Usuario;
import Negocio.NegocioCliente;

public class NegocioClienteImp implements NegocioCliente {

	private DaoCliente daoC = new DaoCliente();
    public boolean registrarCliente(Cliente cliente) {
        return daoC.agregarCliente(cliente);
    }

	@Override
	public Usuario obtieneUsuario(String usuario, String pass) {
		return daoC.login(usuario, pass);
	}
	
	public int obtenerIdCuentaPorCBU(String cbu) {
	    
	    return daoC.obtenerIdCuentaPorCBU(cbu);
	}
	
	public boolean registrarTransferencia(int cuentaEmisorID, int cuentaReceptorID, int numero, float monto, String descripcion, java.sql.Date fechaAlta)
	{
	    return daoC.registrarTransferencia(cuentaEmisorID, cuentaReceptorID, numero, monto, descripcion, fechaAlta);

	}
	
	public List<Cuentas> obtenerCuentasPorCliente(int clienteId){
		return daoC.obtenerCuentasPorCliente(clienteId);
	}

}
