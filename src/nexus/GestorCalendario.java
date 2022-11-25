package nexus;

import java.util.List;

import logic.Medico;
import logic.jornada.Calendario;
import util.DataBase;

public class GestorCalendario {
	private DataBase db = new DataBase();

	public List<Calendario> cargarCalendarios() {
		return db.cargarCalendarios();
	}

	public void guardar(Calendario c) {
		db.guardarCalendario(c);
	}

	public void eliminarCalendario(Calendario c) {
		db.eliminarCalendario(c);
	}

	public void guardarCalendarioParaMedicos(Calendario c, List<Medico> meds) {
		db.guardarCalendarioParaMedicos(c, meds);
	}

	public String generarId() {
		return db.generarIdCalendario();
	}

	public void editar(Calendario c) {
		db.editarCalendario(c);
	}

}
