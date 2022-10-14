package logic;

import java.util.Calendar;
import java.util.Date;

public class Cita {

	private int idCita;
	private int idPaciente;
	private String nombrePaciente;
	private Date fecha;
	private Date horaInicio;
	private Date horaFin;
	private boolean urgente;
	private int sala;
	private String telefono;
	private String correo;
	private String otros;
	private boolean acudio;
	private String causa;

	public Cita(int id, String n, String f, String h, int s, boolean u) {
		idCita = generarId();
		setIdPaciente(id);
		setNombre(n);
		setFecha(f);
		setHoraInicio(h);
		setSala(s);
		setUrgente(u);

	}

	public Cita(int id, int pacienteId, String fecha, String horaI, String horaF, boolean urgente, int salaId,
			String telefono, String correo, String otros, boolean acudio, String causa) {
		this.idCita = id;
		this.idPaciente = pacienteId;
		setFecha(fecha);
		setHoraInicio(horaI);
		setHoraFin(horaF);
		this.urgente = urgente;
		this.sala = salaId;
		this.telefono = telefono;
		this.correo = correo;
		this.otros = otros;
		this.acudio = acudio;
		this.causa = causa;
	}

	private int generarId() {
		int n = (int) (Math.random() * 1000 + 1);
		return n;
	}

	public void setNombre(String str) {
		nombrePaciente = str;

	}

	public void setIdPaciente(int i) {
		this.idPaciente = i;
	}

	public void setUrgente(boolean u) {
		urgente = u;
	}

	public void setSala(int num) {
		this.sala = num;
	}

	/*
	 * Pasa del string guardado en bd a un objeto del tipo fecha Formato: DD/MM/YYYY
	 * 0 1 2
	 */
	public void setFecha(String fecha) {
		String[] sep = fecha.split("/");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sep[0]));
		c.set(Calendar.MONTH, Integer.parseInt(sep[1]));
		c.set(Calendar.YEAR, Integer.parseInt(sep[2]));

		this.fecha = c.getTime();
	}

	/*
	 * HH:MM 0 1
	 */
	public void setHoraInicio(String hora) {
		String[] sep = hora.split(":");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, Integer.parseInt(sep[0]));
		c.set(Calendar.MINUTE, Integer.parseInt(sep[1]));
		this.horaInicio = c.getTime();
	}

	/*
	 * HH:MM 0 1
	 */
	public void setHoraFin(String hora) {
		String[] sep = hora.split(":");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, Integer.parseInt(sep[0]));
		c.set(Calendar.MINUTE, Integer.parseInt(sep[1]));
		this.horaFin = c.getTime();
	}

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public boolean isAcudio() {
		return acudio;
	}

	public void setAcudio(boolean acudio) {
		this.acudio = acudio;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public boolean isUrgente() {
		return urgente;
	}

	public int getSala() {
		return sala;
	}

	public Date getFecha() {
		return fecha;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}
}
