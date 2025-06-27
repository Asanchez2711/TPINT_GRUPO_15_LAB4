package NegocioImp;

import Dao.DaoPrestamo;
import Entidades.Prestamo;
import Negocio.NegocioPrestamo;

public class NegocioPrestamoImp implements NegocioPrestamo{

	private DaoPrestamo daoP;
	@Override
	public boolean PrestamoSolicitar(Prestamo prestamo) {
		
		daoP = new DaoPrestamo();
		if (prestamo.getCuenta().isActivo() != true) return false;
		if (prestamo.getCliente().isActivo() != true) return false;
		
		if (daoP.prestamoSolicitar(prestamo)) return true;
		
		return false;
	}
	@Override
	public boolean PrestamoConfirmar(Prestamo prestamo) {
		
		daoP = new DaoPrestamo();
		if (prestamo.isActivoOK() != true) return false;
		if (prestamo.getCuenta().isActivo() != true) return false;
		if (prestamo.getCliente().isActivo() != true) return false;
		
		if (daoP.prestamoConfirmar(prestamo)) return true;
		
		return false;
	}

}
