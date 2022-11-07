package logic.jornada;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendario {
	private String id;
	private Date inicio;
	private Date fin;

	public Calendario(String id, Date inicio, Date fin) {
		this.id = id;
		this.inicio = inicio;
		this.fin = fin;
	}

	public Calendario(Date inicio, Date fin) {
		this.inicio = inicio;
		this.fin = fin;
	}

	public Calendario(String id, String i, String f) {
		this.id = id;
		inicio = parseDate(i);
		fin = parseDate(f);
	}

	private Date parseDate(String s) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(s);
		} catch (ParseException e) {
			return null;
		}

	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	public Date getDiaInicio() {
		return inicio;
	}

	public Date getDiaFin() {
		return fin;
	}

	@Override
	public String toString() {
		return "Calendario [inicio="
				+ new SimpleDateFormat("dd/MM/yyyy").format(inicio) + ", fin="
				+ new SimpleDateFormat("dd/MM/yyyy").format(fin) + "]";
	}

	public String getId() {
		return id;
	}
}
