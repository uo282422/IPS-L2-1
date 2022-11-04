package ui;

public class Especialidad {
	private String nombre_esp;
	private String id_esp;
	private int uds;
	public Especialidad(String id,String nom) {
		this.id_esp=id;
		this.nombre_esp=nom;
		this.uds=0;
	}
	
	public void aumentarUnidades() {
		uds++;
	}
	
	public int getUnidades() {
		return uds;
	}

	public String getNombre_esp() {
		return nombre_esp;
	}

	public void setNombre_esp(String nombre_esp) {
		this.nombre_esp = nombre_esp;
	}

	public String getId_esp() {
		return id_esp;
	}

	public void setId_esp(String id_esp) {
		this.id_esp = id_esp;
	}
	
	

}
