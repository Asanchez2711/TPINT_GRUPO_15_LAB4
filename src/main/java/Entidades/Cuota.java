package Entidades;

import java.math.BigDecimal;

public class Cuota {
	private int id;
	private Movimiento movimiento;
	private Prestamo prestamo;
	private int CuotaNumero;
	private BigDecimal monto;
	private boolean ActivoOK;

	public Cuota() {
		super();
	}

	public Cuota(int id, Movimiento movimiento, Prestamo prestamo, int cuotaNumero, BigDecimal monto,
			boolean activoOK) {
		super();
		this.id = id;
		this.movimiento = movimiento;
		this.prestamo = prestamo;
		CuotaNumero = cuotaNumero;
		this.monto = monto;
		ActivoOK = activoOK;
	}

	public int getId() {
		return id;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public int getCuotaNumero() {
		return CuotaNumero;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public boolean isActivoOK() {
		return ActivoOK;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public void setCuotaNumero(int cuotaNumero) {
		CuotaNumero = cuotaNumero;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public void setActivoOK(boolean activoOK) {
		ActivoOK = activoOK;
	}

	@Override
	public String toString() {
		return "Cuota [id=" + id + ", movimiento=" + movimiento + ", prestamo=" + prestamo + ", CuotaNumero="
				+ CuotaNumero + ", monto=" + monto + ", ActivoOK=" + ActivoOK + "]";
	}
}
