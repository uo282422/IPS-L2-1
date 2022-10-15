package logic;

public class Sala {
	private int id;
	private String nombre;
	
	public Sala(int id, String nombre) {
		
		this.id=id;
		this.nombre=nombre;
	}
	
	public String getSalaNombre() {return nombre;}
	public int getSalaId() {return id;}
	
	@Override
	public String toString() {
		return nombre;
	}
}
