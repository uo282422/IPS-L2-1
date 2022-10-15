package nexus;

import java.util.ArrayList;

import logic.Paciente;
import util.DataBase;

public class GestorPacientes {
	private DataBase bd;
	public GestorPacientes() {
		this.bd=new DataBase();
		listaPacientes = bd.cargarPacientes();
		// listaPacientes.add(new Paciente(12,"Juan","Iglesias", 999, "correito","otro
		// contacto 1"));
	}

	private ArrayList<Paciente> listaPacientes = new ArrayList<>();

	public void actualizarTelefonoPaciente(int id, int num) {
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				p.setTelefono(num);
			}
		}
	}
	public Paciente getPaciente(int id) {
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	public void actualizarCorreoPaciente(int id, String mail) {
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				p.setCorreo(mail);
			}
		}
	}

	public void actualizarOtrosContactosPaciente(int id, String otro) {
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				p.setOtrosContactos(otro);
			}
		}
	}

	public String getTelefonoPaciente(int id) {
		String str = "";
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				str += p.getTelefono();
			}
		}
		return str;
	}

	public String getOtrosContactosPaciente(int id) {
		String str = "";
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				str = p.getOtrosContactos();
			}
		}
		return str;
	}

	public String getCorreoPaciente(int id) {
		String str = "";
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				str = p.getCorreo();
			}
		}
		return str;

	}

	public int buscarIdPaciente(String n, String a) {
		int i = -1;
		for (Paciente p : listaPacientes) {
			if (p.getNombre().equals(n) && p.getApellido().equals(a)) {
				i = p.getId();
			}
		}
		return i;
	}

	public ArrayList<Paciente> getListaPacientes() {
		return this.listaPacientes;
	}
}
