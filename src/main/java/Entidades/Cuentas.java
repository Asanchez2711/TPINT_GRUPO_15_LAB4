package Entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cuentas {
	private int idCuenta;
	private Cliente ClienteID;
	private int tipocuenta;
	private int numero;
	private String cbu;
	private LocalDate fechaAlta;
	private BigDecimal Saldo;
	private boolean activo;
	
	public Cuentas() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cuentas(int idCuenta, Cliente clienteID, int tipocuenta, int numero, String cbu, LocalDate fechaAlta,
			BigDecimal saldo, boolean activo) {
		super();
		this.idCuenta = idCuenta;
		ClienteID = clienteID;
		this.tipocuenta = tipocuenta;
		this.numero = numero;
		this.cbu = cbu;
		this.fechaAlta = fechaAlta;
		Saldo = saldo;
		this.activo = activo;
	}
	
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public Cliente getClienteID() {
		return ClienteID;
	}
	public void setClienteID(Cliente clienteID) {
		ClienteID = clienteID;
	}
	public int getTipocuenta() {
		return tipocuenta;
	}
	public void setTipocuenta(int tipocuenta) {
		this.tipocuenta = tipocuenta;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public BigDecimal getSaldo() {
		return this.Saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		Saldo = saldo;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	
}
