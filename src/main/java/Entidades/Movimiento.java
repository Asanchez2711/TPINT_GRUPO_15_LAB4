package Entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Movimiento {
	private int id;
    private int cuentaID;
    private int tipoMovimiento;
    private int numero;
    private BigDecimal monto;
    private String descripcion;
    private LocalDate fecha;
    private boolean activo;

    // Constructor vacío
    public Movimiento() {}
    
    
    
	public Movimiento(int id, int cuentaID, int tipoMovimiento, int numero, BigDecimal monto, String descripcion,
			LocalDate fecha, boolean activo) {
		super();
		this.id = id;
		this.cuentaID = cuentaID;
		this.tipoMovimiento = tipoMovimiento;
		this.numero = numero;
		this.monto = monto;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.activo = activo;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCuentaID() {
		return cuentaID;
	}

	public void setCuentaID(int cuentaID) {
		this.cuentaID = cuentaID;
	}

	public int getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(int tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
    
    
}
