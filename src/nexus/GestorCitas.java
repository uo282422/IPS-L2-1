package nexus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import logic.Cita;
import util.DataBase;

public class GestorCitas {

	private DataBase db;

	private ArrayList<Cita> listaCitas = new ArrayList<>();

	public GestorCitas() {
		this.db = new DataBase();
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
	
	public List<Cita> cargarCitasOrdenadas(String idMedico, String fecha) {

		List<Cita> citas = db.cargarCitasPorMedicoYFecha(idMedico, fecha);
		
		Collections.sort(citas, (o1, o2) -> (
				Integer.parseInt(o1.getHoraInicio().split(":")[0]) - Integer.parseInt(o2.getHoraInicio().split(":")[0]) == 0 ?
						Integer.parseInt(o1.getHoraInicio().split(":")[1]) - Integer.parseInt(o2.getHoraInicio().split(":")[1]) :
							Integer.parseInt(o1.getHoraInicio().split(":")[0]) - Integer.parseInt(o2.getHoraInicio().split(":")[0])
				));
		
		// debug
		for (Cita c : citas) {
			System.out.println(c);
		}

		return citas;
	}

}
