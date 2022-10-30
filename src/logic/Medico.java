package logic;

public class Medico {

	private String id;
	private String nombre;
	private String apellido;
	private String email;
	private String especialidad;
	
	public Medico(String id, String nombre, String apellido, String email, String esp) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.especialidad=esp;
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
	
	public String toString() {
		return String.format("%s %s", getNombre(), getApellido());
	}
}
