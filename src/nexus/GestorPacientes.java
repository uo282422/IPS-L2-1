package nexus;

import java.util.ArrayList;

import logic.Paciente;
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
}
