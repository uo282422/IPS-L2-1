package nexus;

import java.util.List;

import logic.Sala;
import util.DataBase;

public class GestorSalas {
	private List<Sala>listaSalas;
	private DataBase bd;
	public GestorSalas() {
		this.bd=new DataBase();
		listaSalas=bd.cargarSalas();
	}

	public List<Sala> getListaSalas(){
		return listaSalas;
	}
}
