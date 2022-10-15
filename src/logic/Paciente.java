package logic;

import java.util.Optional;

public class Paciente {
	private int id;
	private int telefono;
	private String correo;
	private String otrosContactos;
	private String nombre;
	private String apellido;
	public Paciente(int id, String name,String surname, int telefono, String correo,  String otros ) {
		this.id=id;
		this.telefono=telefono;
		this.correo=correo;
		this.otrosContactos=otros;
		this.nombre=name;
		this.apellido=surname;
	}
	
	public void actualizarDatos(String str) {
		String[] sep=str.split("\n");
		telefono=Integer.parseInt(sep[0]);
		correo=sep[1];
		System.out.println(telefono);
		System.out.println(correo);
	}
	public String getApellido() {
		return apellido;
	}
	public String getNombre() {
		return this.nombre;
	}
	public int getId() {
		return id;
	}
	public int getTelefono() {
		return telefono;
	}
	public String getOtrosContactos() {
		return otrosContactos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setOtrosContactos(String str) {
		this.otrosContactos=str;
	}
	public void setTelefono(int n) {
		this.telefono=n;
	}
	public void setCorreo(String str) {
		this.correo=str;
	}
}
