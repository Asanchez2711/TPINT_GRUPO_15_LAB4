package Dao;

import java.io.IOException;

import java.sql.CallableStatement;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Date;
import Entidades.Cliente;
import Entidades.Cuentas;
import Entidades.Movimiento;
import Entidades.Usuario;
import Otros.enmTipos;

public class DaoUsuario {

	private Connection con;

	public DaoUsuario() {

	}

	public List<Map<String, Object>> informePorcentajeProvincias() {

		List<Map<String, Object>> resultados = new ArrayList<Map<String, Object>>();

		String sql = "", sqlCampos = "", sqlFrom = "", sqlGroupby = "", sqlOrderby = "";

		sqlCampos = sqlCampos + "SELECT " + "  provincia as Provincia, " + "  COUNT(Provincia) AS Cantidad, "
				+ "  ROUND(COUNT(Provincia) * 100.0 / total.total_cant, 2) AS Porcentaje ";

		sqlFrom = sqlFrom + " FROM Clientes, ( " + "  SELECT COUNT(Provincia) AS total_cant FROM Clientes "
				+ " ) AS total ";

		sqlGroupby = sqlGroupby + " GROUP BY provincia ";

		sqlOrderby = sqlOrderby + " ORDER BY Cantidad DESC; ";

		sql = sqlCampos + sqlFrom + sqlGroupby + sqlOrderby;

		Statement stmt = null;
		try {
			this.con = Conexion.getConexion();
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			ResultSetMetaData meta = rs.getMetaData();
			int columnas = meta.getColumnCount();

			while (rs.next()) {
				Map<String, Object> fila = new HashMap<>();
				for (int i = 1; i <= columnas; i++) {
					String nombreColumna = meta.getColumnName(i); // o getColumnName(i)
					Object valor = rs.getObject(i);
					fila.put(nombreColumna, valor);
				}
				resultados.add(fila);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
//			resultado = false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}

		return resultados;
	}

	public List<Map<String, Object>> informePorcentajeNacionalidades() {

		List<Map<String, Object>> resultados = new ArrayList<Map<String, Object>>();

		String sql = "", sqlCampos = "", sqlFrom = "", sqlGroupby = "", sqlOrderby = "";

		sqlCampos = sqlCampos + "SELECT " + "  Nacionalidad as Nacionalidad, " + "  COUNT(Nacionalidad) AS Cantidad, "
				+ "  ROUND(COUNT(Nacionalidad) * 100.0 / total.total_cant, 2) AS Porcentaje  ";

		sqlFrom = sqlFrom + " FROM Clientes, ( " + "  SELECT COUNT(Nacionalidad) AS total_cant FROM Clientes "
				+ ") AS total ";

		sqlGroupby = sqlGroupby + " GROUP BY Nacionalidad ";

		sqlOrderby = sqlOrderby + " ORDER BY Cantidad DESC; ";

		sql = sqlCampos + sqlFrom + sqlGroupby + sqlOrderby;

		Statement stmt = null;
		try {
			this.con = Conexion.getConexion();
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			ResultSetMetaData meta = rs.getMetaData();
			int columnas = meta.getColumnCount();

			while (rs.next()) {
				Map<String, Object> fila = new HashMap<>();
				for (int i = 1; i <= columnas; i++) {
					String nombreColumna = meta.getColumnName(i); // o getColumnName(i)
					Object valor = rs.getObject(i);
					fila.put(nombreColumna, valor);
				}
				resultados.add(fila);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}

		return resultados;
	}

	public List<Map<String, Object>> informePorcentajeEdades() {

		List<Map<String, Object>> resultados = new ArrayList<Map<String, Object>>();

		String sql = "", sqlCampos = "", sqlFrom = "", sqlGroupby = "", sqlOrderby = "";

		sqlCampos = sqlCampos + "SELECT " + "  CONCAT(r.inicio, ' - ', r.inicio + 9) AS Rango_edad, "
				+ "  COUNT(c.fechaNacimiento) AS Cantidad, "
				+ "  ROUND(COUNT(c.fechaNacimiento) * 100.0 / total.total_cant, 2) AS Porcentaje  ";

		sqlFrom = sqlFrom + " FROM ( " + "  SELECT 18 AS inicio UNION ALL " + "  SELECT 28 UNION ALL "
				+ "  SELECT 38 UNION ALL " + "  SELECT 48 UNION ALL " + "  SELECT 58 UNION ALL "
				+ "  SELECT 68 UNION ALL " + "  SELECT 78 UNION ALL " + "  SELECT 88 UNION ALL " + "  SELECT 98 "
				+ ") AS r " + "LEFT JOIN ( " + "  SELECT fechaNacimiento, "
				+ "         TIMESTAMPDIFF(YEAR, fechaNacimiento, CURDATE()) AS edad " + "  FROM clientes "
				+ "  WHERE TIMESTAMPDIFF(YEAR, fechaNacimiento, CURDATE()) BETWEEN 18 AND 98 " + ") AS c "
				+ "  ON TIMESTAMPDIFF(YEAR, c.fechaNacimiento, CURDATE()) BETWEEN r.inicio AND r.inicio + 9 "
				+ "JOIN ( " + "  SELECT COUNT(*) AS total_cant " + "  FROM clientes "
				+ "  WHERE TIMESTAMPDIFF(YEAR, fechaNacimiento, CURDATE()) BETWEEN 18 AND 98 " + ") AS total ";

		sqlGroupby = sqlGroupby + " GROUP BY r.inicio " + "HAVING cantidad > 0 ";

		sqlOrderby = sqlOrderby + " ORDER BY r.inicio; ";

		sql = sqlCampos + sqlFrom + sqlGroupby + sqlOrderby;

		Statement stmt = null;
		try {
			this.con = Conexion.getConexion();
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			ResultSetMetaData meta = rs.getMetaData();
			int columnas = meta.getColumnCount();

			while (rs.next()) {
				Map<String, Object> fila = new HashMap<>();
				for (int i = 1; i <= columnas; i++) {
					String nombreColumna = meta.getColumnName(i); // o getColumnName(i)
					Object valor = rs.getObject(i);
					fila.put(nombreColumna, valor);
				}
				resultados.add(fila);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}

		return resultados;
	}

	public List<Usuario> listarUsuarios() {
		List<Usuario> lista = new ArrayList<>();
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;

		try {
			this.con = Conexion.getConexion();
			stmt = con.prepareCall("{CALL sp_listar_usuarios()}");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setUsuario(rs.getString("usuario"));
				u.setPass(rs.getString("pass"));
				u.setTipoUsuario(rs.getInt("tipoUsuario"));
				u.setActivoOK(rs.getBoolean("activoOK"));
				lista.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
}
