package nexus;

import java.util.ArrayList;

import logic.Paciente;

public class GestorPacientes {
	public GestorPacientes() {
		listaPacientes.add(new Paciente(12,999, "correito"));
	}

	private ArrayList<Paciente> listaPacientes=new ArrayList<>();
	
	public void actualizarContactoPaciente(int id, String contacto) {
		for(Paciente p: listaPacientes) {
			if(p.getId()==id) {
				p.actualizarDatos(contacto);
			}
		}
	}
	public String getContactoPaciente(int id) {
		String str="";
		for(Paciente p: listaPacientes) {
			if(p.getId()==id) {
				str=p.getTelefono()+"\n"+p.getCorreo();
			}
		}
		return str;
	}
}
