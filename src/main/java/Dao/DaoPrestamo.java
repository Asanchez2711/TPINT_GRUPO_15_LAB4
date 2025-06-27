package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entidades.Cliente;
import Entidades.Prestamo;
import Entidades.Usuario;
import Otros.enmTipos;

public class DaoPrestamo {
	
	private Connection con;

	public boolean prestamoSolicitar(Prestamo prestamo) {
		boolean resultado = false;
		CallableStatement stmt = null;
		
		try {
			this.con = Conexion.getConexion();
			this.con.setAutoCommit(false);

			stmt = this.con.prepareCall("{ call abmPrestamoSolicitud(?,?,?,?,?,?) }");

			stmt.setInt(1, prestamo.getCliente().getIdCliente());
			stmt.setInt(2, prestamo.getCuenta().getIdCuenta());
			stmt.setBigDecimal(3, prestamo.getMontoSolicitado());
			stmt.setObject(4, Date.valueOf(prestamo.getFechaSolicitud()));
			stmt.setInt(5, prestamo.getPlazo());
			stmt.setBoolean(6, prestamo.isActivoOK());
			
			stmt.execute();

			con.commit();
			resultado = true;

		} catch (Exception e) {
			try {
				if (this.con != null) this.con.rollback();
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
				System.out.println(rollbackEx.getMessage());
			}
			System.out.println(e.getMessage());
			resultado = false;
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (this.con != null) this.con.setAutoCommit(true);
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}
		return resultado;
	}
	
	
	public boolean prestamoConfirmar(Prestamo prestamo) {
		boolean resultado = false;
		CallableStatement stmt = null;
		
		try {
			this.con = Conexion.getConexion();
			this.con.setAutoCommit(false);

			stmt = this.con.prepareCall("{ call abmPrestamoRespuesta(?,?,?,?,?,?) }");

			stmt.setInt(1, prestamo.getId());
			stmt.setBoolean(2, prestamo.isRespuesta());
			stmt.setObject(3, Date.valueOf(prestamo.getFechaRespuesta()));
			stmt.setBigDecimal(4, prestamo.getMontoAprobado());
			stmt.setBigDecimal(5, prestamo.getMontoCuota());
			stmt.setBoolean(6, prestamo.isActivoOK());
			
			stmt.execute();

			con.commit();
			resultado = true;

		} catch (Exception e) {
			try {
				if (this.con != null) this.con.rollback();
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
				System.out.println(rollbackEx.getMessage());
			}
			System.out.println(e.getMessage());
			resultado = false;
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (this.con != null) this.con.setAutoCommit(true);
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}
		
		return resultado;
	}
	
}
