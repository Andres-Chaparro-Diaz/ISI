package Dominio;

public class Usuario {

	private String nombre, correo, telefono, tipo, contrasena, ultConexion;
	
	public Usuario(String nombre, String correo, String telefono, String tipo, String contrasena, String ultConexion) {
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.tipo = tipo;
		this.contrasena=contrasena;
		this.ultConexion = ultConexion;
	}
	public Usuario() {
		
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUltConexion() {
		return ultConexion;
	}

	public void setUltConexion(String ultConexion) {
		this.ultConexion = ultConexion;
	}
	
}