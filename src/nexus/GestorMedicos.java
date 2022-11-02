package nexus;

import java.util.ArrayList;

import logic.Medico;
import ui.Especialidad;
import util.DataBase;

public class GestorMedicos {

	private ArrayList<Medico> medicos = new ArrayList<>();
	private DataBase bd;

	public GestorMedicos() {
		this.bd = new DataBase();
		medicos = new ArrayList<Medico>(bd.cargarMedicos());
	}

	public ArrayList<Medico> getMedicos() {
		return medicos;
	}

	
}
