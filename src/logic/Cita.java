package logic;

import java.util.Calendar;
import java.util.Date;

public class Cita {

	private int idCita;
	private int idPaciente;
	private Date fecha;
	private Date horaE;
	private Date horaS;
	private boolean urgente;
	private int sala;
	
	public Cita(int id,  String f,String he, String hs, int s, boolean u) {
		idCita=generarId();
		setIdPaciente(id);
		setFecha(f);
		setHoraE(he);
		setHoraS(hs);
		setSala(s);
		setUrgente(u);
		
	}

	public Date getDia() {
		return fecha;
	}
	public Date getHoraE() {
		return horaE;
	}
	public Date getHoraS() {
		return horaS;
	}
	private int generarId() {
		int n=(int) (Math.random()*1000+1);
		return n;
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
	 * 		DD/MM/YYYY
	 * 		0 1  2  
	 */
	public void setFecha(String fecha) {
		String[] sep=fecha.split("/");
		Calendar c=Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sep[0]));
		
		c.set(Calendar.MONTH, Integer.parseInt(sep[1])-1);
		c.set(Calendar.YEAR, Integer.parseInt(sep[2]));
		
		this.fecha=c.getTime();
	}
	/*
	 * 
	 * HH:MM
	 * 0  1
	 */
	public void setHoraE(String e) {
		String[] sep=e.split(":");
		Calendar c=Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sep[0]));
		c.set(Calendar.MINUTE, Integer.parseInt(sep[1]));
		this.horaE=c.getTime();
	}
	public void setHoraS(String s) {
		String[] sep=s.split(":");
		Calendar c=Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sep[0]));
		c.set(Calendar.MINUTE, Integer.parseInt(sep[1]));
		this.horaS=c.getTime();
	}
}
