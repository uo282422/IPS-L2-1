package logic.diagnostico;

public class DiagnosticoCapitulo {
	
	private String idCapitulo;
	private String nombre;
	
	public DiagnosticoCapitulo(String id, String nombre) {
		this.setIdCapitulo(id);
		this.setNombre(nombre);
	}

	public String getNombre() {	
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdCapitulo() {
		return idCapitulo;
	}

	public void setIdCapitulo(String idCapitulo) {
		this.idCapitulo = idCapitulo;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", idCapitulo, nombre);
	}

}
