package Negocio;

import Entidades.Prestamo;

public interface NegocioPrestamo {

	boolean PrestamoSolicitar(Prestamo prestamo);
	
	boolean PrestamoConfirmar(Prestamo prestamo);
}
