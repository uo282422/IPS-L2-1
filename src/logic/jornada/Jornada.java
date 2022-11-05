package logic.jornada;

import java.util.Date;

import logic.Medico;
import util.DataBase;

public class Jornada {

	private String id;
	private Medico medico;
	private String dias;
	private String horaComienzo;
	private String horaFinal;
	private Calendario c;
	private String medicoId;
	private String calId;

	public Jornada() {
		id = generarId();
	}

	private String generarId() {
		DataBase db = new DataBase();
		int id = Integer.parseInt(
				db.cargarJornadaId().get(db.cargarJornadaId().size() - 1)) + 1;
		return id + "";
	}

	public void setId(String id) {
		this.id = id;
	}

	public Jornada(String id, String dias, String hI, String hF, String medid,
			String calId) {
		this.id = id;
		this.dias = dias;
		this.horaComienzo = hI;
		this.horaFinal = hF;
		this.medicoId = medid;
		this.calId = calId;
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

	public Calendario getCalendario() {
		return c;
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

	public void setCalendario(Calendario c) {
		this.c = c;
	}

	public void setMedico(Medico m) {
		medico = m;
	}

	public void creaCalendario(Date dInicio, Date dFin) {
		c = new Calendario(dInicio, dFin);

	}
}
