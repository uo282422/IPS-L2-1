package logic.procedimiento;

public class ProcedimientoTipo {
	
	private String idTipo;
	private String nombre;
	
	public ProcedimientoTipo(String id, String nombre) {
		this.idTipo = id;
		this.nombre = nombre;
	}

	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", idTipo, nombre);
	}

}
