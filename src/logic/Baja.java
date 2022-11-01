package logic;

public class Baja {

	public enum BajaTipos {
		BAJA, VACACIONES, MOSCOSOS, OTROS;
	}

	private String id;
	private Medico medico;
	private BajaTipos tipo;
	private String fInicio;
	private String fFin;
	private String hInicio;
	private String hFin;
	private String Observaciones;

	public Baja() {
	};

	public Baja(String id, Medico medico, BajaTipos tipo, String fInicio,
			String fFin, String hInicio, String hFin, String observaciones) {
		this.id = id;
		this.medico = medico;
		this.tipo = tipo;
		this.fInicio = fInicio;
		this.fFin = fFin;
		this.hInicio = hInicio;
		this.hFin = hFin;
		Observaciones = observaciones;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
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
