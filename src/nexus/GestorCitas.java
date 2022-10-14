package nexus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logic.Cita;
import util.DataBase;

public class GestorCitas {

	private DataBase db;

	private ArrayList<Cita> listaCitas = new ArrayList<>();

	public GestorCitas(DataBase db) {
		this.db = db;
	}

	public void nuevaCita(int idPaciente, String nombre, String fecha, String hora, int sala, boolean urg) {
		Cita c = new Cita(idPaciente, nombre, fecha, hora, sala, urg);
		listaCitas.add(c);
		if (urg)
			notificarMedico(c);
	}

	private void notificarMedico(Cita c) {
		// Al tener una cita urgente se notifica al medico, en este caso lo simuluaremos
		// pr pantalla por email

	}
	
	public Map<int[], Cita> cargarPosiciones(String idMedico, String fecha) {
		
		Map<String, Cita> citas = cargarCitas(idMedico, fecha);
	
		return null;
		
	}

	private Map<String, Cita> cargarCitas(String idMedico, String fecha) {

		Map<String, Cita> citas = new HashMap<String, Cita>();

		for (Cita c : db.cargarCitasPorMedicoYFecha(idMedico, fecha)) {
			citas.put(String.format("%s-%s", c.getHoraInicio(), c.getHoraFin()), c);
		}

		return citas;
	}

}
