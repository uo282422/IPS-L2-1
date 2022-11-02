package nexus;

import java.util.ArrayList;

import ui.Especialidad;
import util.DataBase;

public class GestorEspecialidades {
	private ArrayList<Especialidad> especialidades = new ArrayList<>();
	private DataBase bd;
	
	public GestorEspecialidades() {
		this.bd = new DataBase();
		especialidades = new ArrayList<Especialidad>(bd.cargarEspecialidades());
	}
	
	public ArrayList<Especialidad> getListaEspecialidades() {
		return especialidades;
		
	}

	public Especialidad buscarPorNombre(String nombre) {
		for(Especialidad e :especialidades) {
			if(e.getNombre_esp().equals(nombre))
				return e;
		}
		return null;
	}

	
}
