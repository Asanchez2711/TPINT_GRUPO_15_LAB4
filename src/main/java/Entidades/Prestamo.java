package Entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Prestamo {
	private int id;
	private Cliente cliente;
	private Cuentas cuenta;
	private BigDecimal montoSolicitado;
	private LocalDate fechaSolicitud;
	private boolean respuesta;
	private LocalDate fechaRespuesta;
	private BigDecimal montoAprobado;
	private BigDecimal montoCuota;
	private int plazo;
	private boolean activoOK;
	
	public Prestamo() {
		super();
	}
	
	public Prestamo(int id, Cliente cliente, Cuentas cuenta, BigDecimal montoSolicitado, LocalDate fechaSolicitud,
			boolean respuesta, LocalDate fechaRespuesta, BigDecimal montoAprobado, BigDecimal montoCuota, int plazo, boolean activoOK) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.cuenta = cuenta;
		this.montoSolicitado = montoSolicitado;
		this.fechaSolicitud = fechaSolicitud;
		this.respuesta = respuesta;
		this.fechaRespuesta = fechaRespuesta;
		this.montoAprobado = montoAprobado;
		this.montoCuota = montoCuota;
		this.plazo = plazo;
		this.activoOK = activoOK;
	}
	
	
	public int getId() {return id;}
	public Cliente getCliente() {return cliente;}
	public Cuentas getCuenta() {return cuenta;}
	public BigDecimal getMontoSolicitado() {return montoSolicitado;}
	public LocalDate getFechaSolicitud() {return fechaSolicitud;}
	public boolean isRespuesta() {return respuesta;}
	public LocalDate getFechaRespuesta() {return fechaRespuesta;}
	public BigDecimal getMontoAprobado() {return montoAprobado;}
	public BigDecimal getMontoCuota() {return montoCuota;}
	public int getPlazo() {return plazo;}
	public boolean isActivoOK() {return activoOK;}	
	
	public void setId(int id) {this.id = id;}
	public void setCliente(Cliente cliente) {this.cliente = cliente;}
	public void setCuenta(Cuentas cuenta) {this.cuenta = cuenta;}
	public void setMontoSolicitado(BigDecimal montoSolicitado) {this.montoSolicitado = montoSolicitado;}
	public void setFechaSolicitud(LocalDate fechaSolicitud) {this.fechaSolicitud = fechaSolicitud;}
	public void setRespuesta(boolean respuesta) {this.respuesta = respuesta;}
	public void setFechaRespuesta(LocalDate fecharespuesta) {this.fechaRespuesta = fecharespuesta;}
	public void setMontoAprobado(BigDecimal montoAprobado) {this.montoAprobado = montoAprobado;}
	public void setMontoCuota(BigDecimal montoCuota) {this.montoCuota = montoCuota;}
	public void setPlazo(int plazo) {this.plazo = plazo;}
	public void setActivoOK(boolean activoOK) {this.activoOK = activoOK;}

	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", cliente=" + cliente + ", cuenta=" + cuenta + ", montoSolicitado="
				+ montoSolicitado + ", fechaSolicitud=" + fechaSolicitud + ", respuesta=" + respuesta
				+ ", fechaRespuesta=" + fechaRespuesta + ", montoAprobado=" + montoAprobado + ", montoCuota="
				+ montoCuota + ", plazo=" + plazo + ", activoOK=" + activoOK + "]";
	}

}
