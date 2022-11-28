package logic.procedimiento;

public class ProcedimientoSistema {
	
	private String idSistema;
	private String nombre;
	
	public ProcedimientoSistema(String id, String nombre) {
		this.idSistema = id;
		this.nombre = nombre;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", idSistema, nombre);
	}

}
