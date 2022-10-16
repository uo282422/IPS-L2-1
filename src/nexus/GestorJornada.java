package nexus;

import java.text.SimpleDateFormat;
import java.util.Date;

import logic.Jornada;
import logic.Medico;
import util.DataBase;

public class GestorJornada {
	private Jornada jornada;
	private Medico m;
	private String hComienzo;
	private String hFin;
	private String dias;
	private String dInicio;
	private String dFin;
	private DataBase db = new DataBase();

	private void setMedicoJornada() {
		jornada.setMedico(m);
	}

	private void setHoraComienzoJornada() {
		jornada.setHoraComienzo(hComienzo);
	}

	private void setHoraFinJornada() {
		jornada.setHoraFinal(hFin);
	}

	private void setDiaInicioJornada() {
		jornada.setDiaInicio(dInicio);
	}

	private void setDiaFinJornada() {
		jornada.setDiaFinal(dFin);
	}

	private void setDiasJornada() {
		jornada.setDias(dias);
	}

	public void parse(String m, String hComienzo, String hFin, Date dInicio,
			Date dFin, String dias) {
		this.m = parseMedico(m);
		this.hComienzo = hComienzo;
		this.hFin = hFin;
		this.dInicio = parseDate(dInicio);
		this.dFin = parseDate(dFin);
		this.dias = dias;
	}

	public void guardarJornada() {
		jornada = new Jornada();
		setId(generarId());
		setMedicoJornada();
		setHoraComienzoJornada();
		setHoraFinJornada();
		setDiaInicioJornada();
		setDiaFinJornada();
		setDiasJornada();
		db.guardarJornada(jornada);
	}

	private void setId(String id) {
		jornada.setId(id);
	}

	private String generarId() {
		DataBase db = new DataBase();
		int id = Integer.parseInt(
				db.cargarJornadaId().get(db.cargarJornadaId().size() - 1)) + 1;
		return id + "";
	}

	private String parseDate(Date d) {
		return new SimpleDateFormat("dd/MM/yyyy").format(d);
	}

//	private String parseHora(String h) {
//		String[] hour = h.trim().split(":");
//		return hour[0];
//	}

	private Medico parseMedico(String m) {
		for (Medico med : db.cargarMedicos()) {
			if (med.toString().equals(m)) {
				return med;
			}
		}
		return null;
	}
}
