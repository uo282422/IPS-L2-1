package logic;

public class Prescripcion {

	private String idPrescripcion;
	private String nombre;
	private String cantidad;
	private int intervalo;
	private int duracion;
	private String otrosDatos;

	public Prescripcion(String idPrescripcion, String nombre, String cantidad, int intervalo, int duracion, String otrosDatos) {
		this.idPrescripcion = idPrescripcion;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.intervalo = intervalo;
		this.duracion = duracion;
		this.otrosDatos = otrosDatos;
	}

	public Prescripcion(String idPrescripcion, String nombre) {
		this.idPrescripcion = idPrescripcion;
		this.nombre = nombre;
		this.cantidad = "";
		this.intervalo = 0;
		this.duracion = 0;
		this.otrosDatos = "";
	}

	public String getIdPrescripcion() {
		return idPrescripcion;
	}

	public void setIdPrescripcion(String idPrescripcion) {
		this.idPrescripcion = idPrescripcion;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public int getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getOtrosDatos() {
		return otrosDatos;
	}

	public void setOtrosDatos(String otrosDatos) {
		this.otrosDatos = otrosDatos;
	}
	
	public String toString() {
		String text = String.format("%s - %s", idPrescripcion, nombre);
		if (cantidad != "") {
			text += String.format(" %s gr", cantidad);
		}
		if (intervalo != 0) {
			text += String.format(", cada %d hr", intervalo);
		}
		if (duracion != 0) {
			text += String.format(" durante %d dias", duracion);
		}
		if (otrosDatos != "") {
			text += String.format("  \n%s", otrosDatos);
		}
		return text;
	}

}
