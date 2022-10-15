package logic;

public class Jornada {

	private String id;
	private Medico medico;
	private String dias;
	private String horaComienzo;
	private String horaFinal;
	private String diaInicio;
	private String diaFinal;
	private String medicoId;

	public Jornada() {
		id = generarId();
	}

	private String generarId() {
		int n = (int) (Math.random() * 1000 + 1);
		return n + "";
	}

	public Jornada(String id, String dias, String hI, String hF, String dI,
			String dF, String medid) {
		this.id = id;
		this.dias = dias;
		this.horaComienzo = hI;
		this.horaFinal = hF;
		this.diaInicio = dI;
		this.diaFinal = dF;
		this.medicoId = medid;
	}

	public String getId() {
		return id;
	}

	public Medico getMedico() {
		return medico;
	}

	public String getDias() {
		return dias;
	}

	public String getHoraComienzo() {
		return horaComienzo;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public String getDiaInicio() {
		return diaInicio;
	}

	public String getDiaFinal() {
		return diaFinal;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public void setHoraComienzo(String horaComienzo) {
		this.horaComienzo = horaComienzo;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public void setDiaInicio(String diaInicio) {
		this.diaInicio = diaInicio;
	}

	public void setDiaFinal(String diaFinal) {
		this.diaFinal = diaFinal;
	}

	public void setMedico(Medico m) {
		medico = m;
	}
}
