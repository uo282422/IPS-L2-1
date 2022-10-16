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

	/**
	 * Crea la jornada y la guarda en la base de datos.
	 */
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

	/**
	 * Llama a la base de datos y recoge el último id generado en una jornada
	 * para crear un id 1+
	 * 
	 * @return String conteniendo dicho id.
	 */
	private String generarId() {
		int id = Integer.parseInt(
				db.cargarJornadaId().get(db.cargarJornadaId().size() - 1)) + 1;
		return id + "";
	}

	/**
	 * Recoge la fecha en Date y la devuelve en String en el formato deseado.
	 * 
	 * @param d Date conteniendo la fecha.
	 * @return String con el objeto Date a String correctamente formateado.
	 */
	private String parseDate(Date d) {
		return new SimpleDateFormat("dd/MM/yyyy").format(d);
	}

//	private String parseHora(String h) {
//		String[] hour = h.trim().split(":");
//		return hour[0];
//	}

	/**
	 * Dado el toString de un medico devuelve el médico correspondiente a este
	 * toString.
	 * 
	 * @param m String conteniendo el médico a devolver.
	 * @return Medico cuyo toString corresponde con el parámetro pasado.
	 */
	private Medico parseMedico(String m) {
		for (Medico med : db.cargarMedicos()) {
			if (med.toString().equals(m)) {
				return med;
			}
		}
		return null;
	}
}
