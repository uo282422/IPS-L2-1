package nexus;

import java.util.ArrayList;
import java.util.List;

import logic.Enfermedad;
import logic.Paciente;
import logic.Vacuna;
import util.DataBase;

public class GestorPacientes {
	
	
	private DataBase bd;
	public GestorPacientes() {
		this.bd = new DataBase();
		//listaPacientes.add(new Paciente(12, 999, "correito"));
		listaPacientes = bd.cargarPacientes();
		// listaPacientes.add(new Paciente(12,"Juan","Iglesias", 999, "correito","otro
		// contacto 1"));
	}

	private ArrayList<Paciente> listaPacientes = new ArrayList<>();

	public void actualizarContactoPaciente(int id, String contacto) {
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				p.actualizarDatos(contacto);
			}
		}
	}
	public void actualizarTelefonoPaciente(int id, int num) {
		for (Paciente p : listaPacientes) {
			if (p.getId() == id) {
				p.setTelefono(num);
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
//	public Paciente getPaciente(int id) {
//		for (Paciente p : listaPacientes) {
//			if (p.getId() == id) {
//				return p;
//			}
//		}
//		return null;
//	}

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

	public Paciente getPaciente(int idPaciente) {
		return bd.cargarPacientePorId(idPaciente);
	}

	public List<Enfermedad> cargarEnfermedades(int idPaciente) {
		return bd.cargarEnfermedadesPorPaciente(idPaciente);
	}

	public List<Vacuna> cargarVacunas(int idPaciente) {
		return bd.cargarVacunasPorPaciente(idPaciente);
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
