package logic.jornada;

import java.util.ArrayList;
import java.util.List;

public class Calendario {
	private String id;
	private String nombre;
	private List<Jornada> jornadas = new ArrayList<Jornada>();

	public Calendario(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre + " | ID: " + id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void addJornada(Jornada j) {
		jornadas.add(j);
	}

	public List<Jornada> getJornadas() {
		return jornadas;
	}

	public void setJornadas(List<Jornada> jornadas) {
		this.jornadas = jornadas;
	}

	public void deleteJornada(Jornada jornada) {
		jornadas.remove(jornada);
	}
}
