package logic.diagnostico;

public class Diagnostico {
	
	private String citaId;
	private String capitulo_id;
	private String fecha;
	private String hora;
	private boolean seguimiento;
	
	private String descripcion;
	
	public Diagnostico(String id, String capitulo, String f, String h, boolean seguimiento) {
		this.citaId=id;
		this.capitulo_id=capitulo;
		this.fecha=f;
		this.hora=h;
		this.seguimiento=seguimiento;
	}


	public String getCitaId() {
		return citaId;
	}


	public void setCitaId(String citaId) {
		this.citaId = citaId;
	}


	public String getcapitulo_id() {
		return capitulo_id;
	}


	public void setcapitulo_id(String capitulo) {
		this.capitulo_id = capitulo;
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


	public boolean getSeguimiento() {
		return seguimiento;
	}


	public void setSeguimiento(boolean seguimiento) {
		this.seguimiento = seguimiento;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public String toString() {
		return String.format("%s - %s - %s - %s", capitulo_id, descripcion, fecha, hora);
	}

	
}
