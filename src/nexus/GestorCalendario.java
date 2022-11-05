package nexus;

import java.util.Date;
import java.util.List;

import logic.jornada.Calendario;
import util.DataBase;

public class GestorCalendario {
	private DataBase db = new DataBase();

	public List<Calendario> cargarCalendarios() {
		return db.cargarCalendarios();
	}

	public void guardar(Date i, Date f) {
		Calendario c = new Calendario(db.generarIdCalendario(), i, f);
		db.guardarCalendario(c);
	}

	public void eliminarCalendario(Calendario c) {
		db.eliminarCalendario(c);
	}

}
