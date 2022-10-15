package nexus;

import logic.Sala;
import util.DataBase;

public class GestorSalas {
	
	DataBase db;
	
	public GestorSalas() {
		this.db = new DataBase();
	}
	
	public Sala cargarSala(int id) {
		return db.cargarSalaPorId(id);
	}

}
