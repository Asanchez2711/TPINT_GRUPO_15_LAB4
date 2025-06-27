package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import Entidades.Cuota;

public class DaoCuota {
	
	private Connection con;
	
	public Boolean pagoCuota(Cuota cuota) {
		
		boolean resultado = false;
		
		CallableStatement stmt = null;

		try {
			this.con = Conexion.getConexion();
			this.con.setAutoCommit(false);

			stmt = this.con.prepareCall("{ call abmClienteAlta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

			stmt.execute();

			con.commit();
			resultado = true;

		} catch (Exception e) {

			try {
				if (this.con != null)
					this.con.rollback();
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
			System.out.println(e.getMessage());
			resultado = false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (this.con != null)
					this.con.setAutoCommit(true);
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}
		return false;
	}
}
