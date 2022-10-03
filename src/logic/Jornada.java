package logic;

import java.sql.Time;

public class Jornada {
	
	private Medico medico;
	private String dias;
	private Time horaComienzo;
	private Time horaFinal;
	private String diaInicio;
	private String diaFinal;

	public Medico getMedico() {
		return medico;
	}

	public String getDias() {
		return dias;
	}

	public Time getHoraComienzo() {
		return horaComienzo;
	}

	public Time getHoraFinal() {
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

	public void setHoraComienzo(Time horaComienzo) {
		this.horaComienzo = horaComienzo;
	}

	public void setHoraFinal(Time horaFinal) {
		this.horaFinal = horaFinal;
	}

	public void setDiaInicio(String diaInicio) {
		this.diaInicio = diaInicio;
	}

	public void setDiaFinal(String diaFinal) {
		this.diaFinal = diaFinal;
	}
	
	private void setMedico(Medico m) {
		medico = m;
	}
}
