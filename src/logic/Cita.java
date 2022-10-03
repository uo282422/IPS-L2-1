package logic;

import java.util.Calendar;
import java.util.Date;

public class Cita {

	private int idCita;
	private int idPaciente;
	private String nombrePaciente;
	private Date fecha;
	private Date hora;
	private boolean urgente;
	private int sala;
	
	public Cita(int id, String n, String fecha, int sala, boolean urgente) {
		idCita=generarId();
		setIdPaciente(id);
		setNombre(n);
		setFecha(fecha);
		setHora(fecha);
		setSala(sala);
		setUrgente(urgente);
		
	}

	private int generarId() {
		int n=(int) (Math.random()*1000+1);
		return n;
	}
	
	public void setNombre(String str) {
		nombrePaciente=str;
		
	}
	public void setIdPaciente(int i) {
		this.idPaciente=i;
	}
	public void setUrgente(boolean u) {
		urgente=u;
	}
	public void setSala(int num) {
		this.sala=num;
	}
	
	/*
	 * Pasa del string guardado en bd a un objeto del tipo fecha
	 * Formato:
	 * 		DDMMYYYYHHMM
	 * 		0 1  2  3 4
	 */
	public void setFecha(String fecha) {
		String[] sep=fecha.split("/");
		Calendar c=Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sep[0]));
		c.set(Calendar.MONTH, Integer.parseInt(sep[1]));
		c.set(Calendar.YEAR, Integer.parseInt(sep[2]));
		
		this.fecha=c.getTime();
	}
	
	public void setHora(String fecha) {
		String[] sep=fecha.split("/");
		Calendar c=Calendar.getInstance();
		c.set(Calendar.HOUR, Integer.parseInt(sep[3]));
		c.set(Calendar.MINUTE, Integer.parseInt(sep[4]));
		this.hora=c.getTime();
	}
}
