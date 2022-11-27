package logic.jornada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import logic.Medico;
import util.DataBase;

public class Jornada {

	private String id;
	private String nombre;
	private Medico medico;
	private String dias;
	private String horaComienzo;
	private String horaFinal;
	private String inicio;
	private String fin;
	private String medicoId;
//	private String calId;

	public Jornada() {
		id = generarId();
	}

	private String generarId() {
		DataBase db = new DataBase();
		int id = Integer.parseInt(db.cargarJornadaId().get(db.cargarJornadaId().size()
				- 1)) + 1;
		return id + "";
	}

	public void setId(String id) {
		this.id = id;
	}

	public Jornada(String id, String nombre, String dias, String hI, String hF,
			String i, String f) {
		this.id = id;
		this.nombre = nombre;
		this.dias = dias;
		this.horaComienzo = hI;
		this.horaFinal = hF;
		this.inicio = i;
		this.fin = f;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
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

	@Override
	public String toString() {
		return nombre + "| " + id + " | " + estado();
	}

	public String estado() {
		LocalDate inicio = LocalDate.parse(this.inicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate fin = LocalDate.parse(this.fin, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		if (fin.isBefore(LocalDate.now()))
			return "terminada";
		else if (inicio.isAfter(LocalDate.now()))
			return "por empezar";
		else
			return "en curso";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public void setMedico(Medico m) {
		medico = m;
	}

//	public String getCalId() {
//		return calId;
//	}
}
