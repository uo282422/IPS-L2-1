package logic.diagnostico;

public class DiagnosticoGrupo {

	private String idGrupo;
	private String nombre;
	
	public DiagnosticoGrupo(String id, String nombre) {
		this.setIdGrupo(id);
		this.setNombre(nombre);
	}

	public String getNombre() {	
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", idGrupo, nombre);
	}
	
}
