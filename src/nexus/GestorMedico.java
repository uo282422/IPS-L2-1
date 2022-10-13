package nexus;

import java.util.ArrayList;

import logic.Medico;
import util.DataBase;

public class GestorMedico {
	
	private ArrayList<Medico> medicos = new ArrayList<>();
	
	public GestorMedico(DataBase bd) {
		medicos = new ArrayList<Medico>(bd.cargarMedicos());
//		medicos = new ArrayList<Medico>();
//		medicos.add(new Medico("1","Nombre1","Apellido1","Email1"));
//		medicos.add(new Medico("2","Nombre1","Apellido2","Email2"));
//		medicos.add(new Medico("3","Nombre1","Apellido3","Email3"));
//		medicos.add(new Medico("4","Nombre1","Apellido4","Email4"));
//		medicos.add(new Medico("5","Nombre1","Apellido5","Email5"));
//		medicos.add(new Medico("6","Nombre1","Apellido6","Email6"));
	}

	public ArrayList<Medico> getMedicos() {
		return new ArrayList<Medico>(this.medicos);
	}
}
