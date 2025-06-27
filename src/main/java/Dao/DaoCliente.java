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

public class DaoCliente {

	private Connection con;

	public boolean agregarCliente(Cliente cliente) {

		boolean resultado = false;

		CallableStatement stmt = null;

		try {
			this.con = Conexion.getConexion();
			this.con.setAutoCommit(false);

			stmt = this.con.prepareCall("{ call abmClienteAlta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

			stmt.setString(1, cliente.getUsuario().getUsuario());
			stmt.setString(2, cliente.getUsuario().getPass());
			stmt.setString(3, cliente.getDni());
			stmt.setString(4, cliente.getCuil());
			stmt.setString(5, cliente.getApellido());
			stmt.setString(6, cliente.getNombre());
			stmt.setString(7, cliente.getSexo());
			stmt.setString(8, cliente.getNacionalidad());
			stmt.setDate(9, Date.valueOf(cliente.getFechaAlta()));
			stmt.setDate(10, Date.valueOf(cliente.getFechaNacimiento()));
			stmt.setString(11, cliente.getProvincia());
			stmt.setString(12, cliente.getLocalidad());
			stmt.setString(13, cliente.getDireccion());
			stmt.setString(14, cliente.getMail());
			stmt.setString(15, cliente.getTelefono());
			stmt.setBoolean(16, cliente.isActivo());

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

		return resultado;
	}

	public Usuario login(String usuario, String clave) {
		Usuario u = new Usuario();

		CallableStatement stmt = null;

		try {

			this.con = Conexion.getConexion();
			System.out.println("Conectado. Buscando usuario: " + usuario + ", clave: " + clave);

			stmt = this.con.prepareCall("{ call consultaUsuario_UsrPass(?,?,?) }");

			stmt.setString(1, usuario);
			stmt.setString(2, clave);
			stmt.setBoolean(3, true);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				u.setId(rs.getInt("ID"));
				u.setUsuario(rs.getString("Usuario"));
				u.setPass(rs.getString("Pass"));
				u.setTipoUsuario(rs.getInt("TipoUsuario"));
				u.setActivoOK(rs.getBoolean("ActivoOK"));
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

		return u;
	}
	
	public Cliente obtenerClientePorUsuario(int usuarioId) {
		Cliente cliente = null;
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = Conexion.getConexion();
			
			stmt = con.prepareCall("{ call obtenerClientePorUsuario(?) }");
			stmt.setInt(1, usuarioId);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("ID"));
				cliente.setDni(rs.getString("DNI"));
				cliente.setCuil(rs.getString("CUIL"));
				cliente.setApellido(rs.getString("Apellido"));
	            cliente.setNombre(rs.getString("Nombre"));
	            cliente.setSexo(rs.getString("Sexo"));
	            cliente.setNacionalidad(rs.getString("Nacionalidad"));
	            cliente.setFechaAlta(rs.getDate("FechaAlta").toLocalDate());
	            cliente.setFechaNacimiento(rs.getDate("FechaNacimiento").toLocalDate());
	            cliente.setProvincia(rs.getString("Provincia"));
	            cliente.setLocalidad(rs.getString("Localidad"));
	            cliente.setDireccion(rs.getString("Direccion"));
	            cliente.setMail(rs.getString("Mail"));
	            cliente.setTelefono(rs.getString("Telefono"));
	            cliente.setActivo(rs.getBoolean("ActivoOK"));
			}
					
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar");
	            e.printStackTrace();
	        }
	    }
		return cliente;
	}
	
	public List<Cuentas> obtenerCuentasPorCliente(int clienteId) {
	    List<Cuentas> cuentasList = new ArrayList<>();

		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
		con = Conexion.getConexion();
		
		stmt = con.prepareCall("{ call obtenerCuentasPorCliente(?) }");
		stmt.setInt(1, clienteId);
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Cuentas cuentas = new Cuentas();
			cuentas.setIdCuenta(rs.getInt("ID"));
			cuentas.setTipocuenta(rs.getInt("TipoCuenta"));
			cuentas.setNumero(rs.getInt("Numero"));
			cuentas.setCbu(rs.getString("CBU"));
            cuentas.setFechaAlta(rs.getDate("FechaAlta").toLocalDate());
            cuentas.setSaldo(rs.getBigDecimal("Saldo"));
            cuentas.setActivo(rs.getBoolean("ActivoOK"));
            cuentasList.add(cuentas);
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar");
	            e.printStackTrace();
	        }
	    }
		return cuentasList;
		}
	
	public List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta){
		List<Movimiento> movimientoList = new ArrayList<>();
		
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			con = Conexion.getConexion();
			stmt = con.prepareCall("{ call obtenerMovimientosPorCuenta(?) }");
			stmt.setInt(1, idCuenta);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Movimiento movimiento = new Movimiento();
				movimiento.setId(rs.getInt("ID"));
				movimiento.setCuentaID(rs.getInt("CuentaId"));
				movimiento.setTipoMovimiento(rs.getInt("TipoMovimiento"));
				movimiento.setNumero(rs.getInt("Numero"));
				movimiento.setMonto(rs.getBigDecimal("Monto"));
				movimiento.setDescripcion(rs.getString("Descripcion"));
				movimiento.setFecha(rs.getDate("Fecha").toLocalDate());
				movimiento.setActivo(rs.getBoolean("ActivoOK"));
				movimientoList.add(movimiento);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar");
	            e.printStackTrace();
	        }
	    }
		return movimientoList;
	}
	
	public int obtenerIdCuentaPorCBU(String cbu) {
		int cuentaID = 0;
	    Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
	    try { con = Conexion.getConexion();
	    		
	    		stmt = con.prepareCall("{ call obtenerIdCuentaPorCBU(?) }");
	    		stmt.setString(1, cbu);
	    		rs = stmt.executeQuery();
	    		if (rs.next()) {
	                cuentaID = rs.getInt("ID");
	            }
	    
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
        try {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	    return cuentaID;
	}

	public boolean registrarTransferencia(int cuentaEmisorID, int cuentaReceptorID, int numero, float monto,
			String descripcion, Date fechaAlta) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		Connection con = null;
		CallableStatement stmt = null;
		
		try {
			con = Conexion.getConexion();
			stmt = con.prepareCall("{ call abmMovimientoTransferencia(?, ?, ?, ?, ?, ?, ?) }");
			
			stmt.setInt(1,  cuentaEmisorID);
			stmt.setInt(2, cuentaReceptorID);
			stmt.setInt(3, numero);
			stmt.setFloat(4, monto);
		    stmt.setString(5, descripcion);
		    stmt.setDate(6, fechaAlta);
		    stmt.setBoolean(7, true);
		    
		    stmt.execute();
		    resultado = true;
		}catch (SQLException e) {
		    if (e.getMessage().contains("Saldo insuficiente")) {
		        System.out.println("Transferencia rechazada: saldo insuficiente.");
		    } else {
		        e.printStackTrace();
		    }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
		
		return resultado;
	}
	
	
	public List<Cliente> obtenerClientes() {
	    List<Cliente> lista = new ArrayList<>();
	    Connection con = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        con = Conexion.getConexion();
	        stmt = con.prepareCall("{ call obtenerClientes() }"); // 
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Cliente c = new Cliente();
	            c.setIdCliente(rs.getInt("ID"));
	            c.setDni(rs.getString("DNI"));
	            c.setCuil(rs.getString("CUIL"));
	            c.setApellido(rs.getString("Apellido"));
	            c.setNombre(rs.getString("Nombre"));
	            c.setDireccion(rs.getString("Direccion"));
	            c.setActivo(rs.getBoolean("ActivoOK"));
	            lista.add(c);
	        }
	    } catch (Exception e) {
	        System.out.println("Error en obtenerClientes(): " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return lista;
	}

	
	}
