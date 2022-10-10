package logic;

import java.util.Random;

public class Jornada {

	private String id;
	private Medico medico;
	private String dias;
	private String horaComienzo;
	private String horaFinal;
	private String diaInicio;
	private String diaFinal;

	public Jornada() {
		// Falta un sistema de creación de ids únicos
		id = "" + Math.abs(new Random().nextInt());
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
