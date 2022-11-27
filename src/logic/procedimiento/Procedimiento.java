package logic.procedimiento;

public class Procedimiento {
	
	private String citaId;
	private String tipoId;
	private String fecha;
	private String hora;
	
	private String descripcion;
	
	public Procedimiento(String id, String tipo, String f, String h) {
		this.citaId = id;
		this.tipoId = tipo;
		this.fecha=f;
		this.hora=h;
	}


	public String getCitaId() {
		return citaId;
	}


	public void setCitaId(String citaId) {
		this.citaId = citaId;
	}


	public String getTipoId() {
		return tipoId;
	}


	public void setTipoId(String tipo) {
		this.tipoId = tipo;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public String toString() {
		return String.format("%s - %s - %s - %s", tipoId, descripcion, fecha, hora);
	}

	
}
