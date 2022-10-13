package nexus;

import java.util.ArrayList;

import logic.Cita;
import util.DataBase;

public class GestorCitas {

	private ArrayList<Cita> listaCitas = new ArrayList<>();
	private DataBase db = new DataBase();

	public void nuevaCita(int idPaciente, String nombre, String fecha,
			String hora, int sala, boolean urg) {
		Cita c = new Cita(idPaciente, nombre, fecha, hora, sala, urg);
		listaCitas.add(c);
		if (urg)
			notificarMedico(c);
	}

	private void notificarMedico(Cita c) {
		// Al tener una cita urgente se notifica al medico, en este caso lo
		// simuluaremos pr pantalla por email

	}

	public Cita getCita(String id) {
		return db.getCita(id);
	}

}
