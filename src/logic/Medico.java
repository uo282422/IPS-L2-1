package logic;

public class Medico {

	private String id;
	private String nombre;
	private String apellido;
	private String email;
	private String especialidad;
	
	

	private String dni;
	private String colegiado;
	
	public Medico(String id, String nombre, String apellido, String email, String esp, String dni, String colegiado) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.especialidad=esp;
		
		this.dni=dni;
		this.colegiado=colegiado;
	}

	public String getId() {
		return id;
	}
	public String getEspecialidad() {
		return especialidad;
	}

	
	public void setEspecialidad(String esp) {
		this.especialidad = esp;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getColegiado() {
		return colegiado;
	}

	public void setColegiado(String colegiado) {
		this.colegiado = colegiado;
	}
	
	public String toString() {
		return String.format("%s %s", getNombre(), getApellido());
	}
}
