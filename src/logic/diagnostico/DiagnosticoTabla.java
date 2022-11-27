package logic.diagnostico;

public class DiagnosticoTabla {
	
	private String idTabla;
	private String nombre;
	
	public DiagnosticoTabla(String id, String nombre) {
		this.setIdTabla(id);
		this.setNombre(nombre);
	}

	public String getNombre() {	
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdTabla() {
		return idTabla;
	}

	public void setIdTabla(String idTabla) {
		this.idTabla = idTabla;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", idTabla, nombre);
	}

}
