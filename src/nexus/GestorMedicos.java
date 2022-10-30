package nexus;

import java.util.ArrayList;

import logic.Medico;
import util.DataBase;

public class GestorMedicos {

	private ArrayList<Medico> medicos = new ArrayList<>();
	private ArrayList<String> especialidades = new ArrayList<>();
	private DataBase bd;

	public GestorMedicos() {
		this.bd = new DataBase();
		medicos = new ArrayList<Medico>(bd.cargarMedicos());
		especialidades = new ArrayList<String>(bd.cargarEspecialidades());
	}

	public ArrayList<Medico> getMedicos() {
		return new ArrayList<Medico>(this.medicos);
	}

	public ArrayList<String> getListaEspecialidades() {
		return new ArrayList<String>(this.especialidades);
		
	}
}
