package logic;

public class Baja {

	public enum BajaTipos {
		BAJA, VACACIONES, MOSCOSOS, OTROS;
	}

	private BajaTipos tipo;
	private String fInicio;
	private String fFin;
	private String hInicio;
	private String hFin;
	private String Observaciones;

	public Baja(BajaTipos tipo, String fInicio, String fFin, String hInicio,
			String hFin, String observaciones) {
		this.tipo = tipo;
		this.fInicio = fInicio;
		this.fFin = fFin;
		this.hInicio = hInicio;
		this.hFin = hFin;
		Observaciones = observaciones;
	}

	public BajaTipos getTipo() {
		return tipo;
	}

	public void setTipo(BajaTipos tipo) {
		this.tipo = tipo;
	}

	public String getfInicio() {
		return fInicio;
	}

	public void setfInicio(String fInicio) {
		this.fInicio = fInicio;
	}

	public String getfFin() {
		return fFin;
	}

	public void setfFin(String fFin) {
		this.fFin = fFin;
	}

	public String gethInicio() {
		return hInicio;
	}

	public void sethInicio(String hInicio) {
		this.hInicio = hInicio;
	}

	public String gethFin() {
		return hFin;
	}

	public void sethFin(String hFin) {
		this.hFin = hFin;
	}

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}

}
