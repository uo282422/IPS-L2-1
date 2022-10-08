package logic;

public class Paciente {
	private int id;
	private int telefono;
	private String correo;
	public Paciente(int id, int telefono, String correo) {
		this.id=id;
		this.telefono=telefono;
		this.correo=correo;
	}
	
	public void actualizarDatos(String str) {
		String[] sep=str.split("\n");
		telefono=Integer.parseInt(sep[0]);
		correo=sep[1];
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
}
