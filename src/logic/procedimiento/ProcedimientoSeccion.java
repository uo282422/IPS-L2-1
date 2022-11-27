package logic.procedimiento;

public class ProcedimientoSeccion {
	
	private String idSeccion;
	private String nombre;
	
	public ProcedimientoSeccion(String id, String nombre) {
		this.idSeccion = id;
		this.nombre = nombre;
	}

	public String getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(String idSeccion) {
		this.idSeccion = idSeccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", idSeccion, nombre);
	}

}
