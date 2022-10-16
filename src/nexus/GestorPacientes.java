package nexus;

import java.util.ArrayList;
import java.util.List;

import logic.Enfermedad;
import logic.Paciente;
import logic.Vacuna;
import util.DataBase;

public class GestorPacientes {
	
	DataBase db;
	
	public GestorPacientes() {
		this.db = new DataBase();
		//listaPacientes.add(new Paciente(12, 999, "correito"));
	}

	private ArrayList<Paciente> listaPacientes = new ArrayList<>();

	public void actualizarContactoPaciente(int id, String contacto) {
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				p.actualizarDatos(contacto);
			}
		}
	}

	public String getContactoPaciente(int id) {
		String str = "";
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				str = p.getTelefono() + "\n" + p.getCorreo();
			}
		}
		return str;
	}

	public Paciente getPaciente(int idPaciente) {
		return db.cargarPacientePorId(idPaciente);
	}

	public List<Enfermedad> cargarEnfermedades(int idPaciente) {
		return db.cargarEnfermedadesPorPaciente(idPaciente);
	}

	public List<Vacuna> cargarVacunas(int idPaciente) {
		return db.cargarVacunasPorPaciente(idPaciente);
	}
}
