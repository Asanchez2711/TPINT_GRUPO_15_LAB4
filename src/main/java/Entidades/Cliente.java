package Entidades;

import java.time.LocalDate;

public class Cliente {
	
	private Usuario usuario;
    private int idCliente;
//    private int idUsuario;
    private String dni;
    private String cuil;
    private String apellido;
    private String nombre;
    private String sexo;
    private String nacionalidad;
    private LocalDate fechaAlta;
    private LocalDate fechaNacimiento;
    private String provincia;
    private String localidad;
    private String direccion;
    private String mail;
    private String telefono;
    private boolean activo;

    public Cliente() {}

    public Cliente(Usuario usuario, int idCliente, String dni, String cuil, String apellido, String nombre,
                   String sexo, String nacionalidad, LocalDate fechaAlta, LocalDate fechaNacimiento,
                   String provincia, String localidad, String direccion, String mail, String telefono,
                   boolean activo) {
    	this.usuario = usuario;
    	this.idCliente = idCliente;
        this.dni = dni;
        this.cuil = cuil;
        this.apellido = apellido;
        this.nombre = nombre;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.fechaAlta = fechaAlta;
        this.fechaNacimiento = fechaNacimiento;
        this.provincia = provincia;
        this.localidad = localidad;
        this.direccion = direccion;
        this.mail = mail;
        this.telefono = telefono;
        this.activo = activo;
    }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Usuario getUsuario() {
    	return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
    	this.usuario = usuario;
    }
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

	@Override
	public String toString() {
		return "Cliente [usuario=" + usuario + ", idCliente=" + idCliente + ", dni=" + dni + ", cuil=" + cuil
				+ ", apellido=" + apellido + ", nombre=" + nombre + ", sexo=" + sexo + ", nacionalidad=" + nacionalidad
				+ ", fechaAlta=" + fechaAlta + ", fechaNacimiento=" + fechaNacimiento + ", provincia=" + provincia
				+ ", localidad=" + localidad + ", direccion=" + direccion + ", mail=" + mail + ", telefono=" + telefono
				+ ", activo=" + activo + "]";
	}


}