package logic.diagnostico;

public class DiagnosticoSubgrupo {
	
	private String idSubgrupo;
	private String nombre;
	
	public DiagnosticoSubgrupo(String id, String nombre) {
		this.setIdSubgrupo(id);
		this.setNombre(nombre);
	}

	public String getNombre() {	
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdSubgrupo() {
		return idSubgrupo;
	}

	public void setIdSubgrupo(String idSubgrupo) {
		this.idSubgrupo = idSubgrupo;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", idSubgrupo, nombre);
	}

}
