package logic;

public class Diagnostico {
	
	private String citaId;
	private String apartado_id;
	private String fecha;
	private String hora;
	private boolean seguimiento;
	
	
	public Diagnostico(String id, String apartado, String f, String h, boolean seguimiento) {
		this.citaId=id;
		this.apartado_id=apartado;
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


	public String getApartado_id() {
		return apartado_id;
	}


	public void setApartado_id(String apartado_id) {
		this.apartado_id = apartado_id;
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

	
}
