package logic;

public class Paciente {
	private int id;
	private String nombre;
	private String apellido;
	private int telefono;
	private String correo;
	private String otros;

	public Paciente(int id, String nombre, String apellido, int telefono, String correo, String otros) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.correo = correo;
		this.otros = otros;
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

	public void actualizarDatos(String str) {
		String[] sep = str.split("\n");
		telefono = Integer.parseInt(sep[0]);
		correo = sep[1];
		System.out.println(telefono);
		System.out.println(correo);
	}

	public int getId() {
		return id;
	}

	public int getTelefono() {
		return telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}
}
