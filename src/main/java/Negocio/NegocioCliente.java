package Negocio;

import java.util.List;

import Entidades.Cliente;
import Entidades.Cuentas;
import Entidades.Usuario;

public interface NegocioCliente {

	boolean registrarCliente(Cliente cliente);
	
	Usuario obtieneUsuario(String usuario, String pass);
	
	int obtenerIdCuentaPorCBU(String cbu); 
	
	public boolean registrarTransferencia(int cuentaEmisorID, int cuentaReceptorID, int numero, float monto, String descripcion, java.sql.Date fechaAlta);
	
	public List<Cuentas> obtenerCuentasPorCliente(int clienteId);

}
