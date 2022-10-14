package logic;

import java.util.Calendar;
import java.util.Date;

public class Cita {

	private int idCita;
	private int idPaciente;
	private String fecha;
	private String horaE;
	private String horaS;
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

	private void setHoraS(String hs) {
		this.horaS=hs;
		
	}

	private void setHoraE(String he) {
		this.horaE=he;
		
	}

	private void setFecha(String f) {
		this.fecha=f;
	}

	public String getDia() {
		return fecha;
	}
	public String getHoraE() {
		return horaE;
	}
	public String getHoraS() {
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
	public Date fechaToDate(String fecha) {
		String[] sep=fecha.split("/");
		Calendar c=Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sep[0]));
		
		c.set(Calendar.MONTH, Integer.parseInt(sep[1])-1);
		c.set(Calendar.YEAR, Integer.parseInt(sep[2]));
		
		Date f=c.getTime();
		return f;
	}
	/*
	 * 
	 * HH:MM
	 * 0  1
	 */
	public Date stringToDate(String e) {
		String[] sep=e.split(":");
		Calendar c=Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sep[0]));
		c.set(Calendar.MINUTE, Integer.parseInt(sep[1]));
		Date h=c.getTime();
		return h;
	}
	
}
