package Otros;

import Entidades.Cliente;
import Entidades.Usuario;
import Negocio.NegocioCliente;
import NegocioImp.NegocioClienteImp;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import Dao.DaoUsuario;

public class Principal {

	
	//Main nomas para probar el negocio
	public static void main(String[] args) {
		
		DaoUsuario dao = new DaoUsuario();
		List<Map<String, Object>> informe = dao.informePorcentajeProvincias();

		for (Map<String, Object> fila : informe) {
		    System.out.println("Provincia: " + fila.get("Provincia"));
		    System.out.println("Cantidad: " + fila.get("Cantidad"));
		    System.out.println("Porcentaje: " + fila.get("Porcentaje"));
		    System.out.println("----");
		}
		

	}	
		

}
