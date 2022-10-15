package logic;

import java.util.Calendar;
import java.util.Date;

public class Cita {

	private int idCita;
	private int idPaciente;
	private String nombrePaciente;
	private String fecha;
	private String horaInicio;
	private String horaFin;
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
		setSala(s);
		setUrgente(u);

	}

	public Cita(int id, int pacienteId, String fecha, String horaI, String horaF, boolean urgente, int salaId,
			String telefono, String correo, String otros, boolean acudio, String causa) {
		this.idCita = id;
		this.idPaciente = pacienteId;
		this.fecha = fecha;
		this.horaInicio = horaI;
		this.horaFin = horaF;
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

	public String getFecha() {
		return fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	@Override
	public String toString() {
		return "Cita [idCita=" + idCita + ", idPaciente=" + idPaciente + ", nombrePaciente=" + nombrePaciente
				+ ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", urgente=" + urgente
				+ ", sala=" + sala + ", telefono=" + telefono + ", correo=" + correo + ", otros=" + otros + ", acudio="
				+ acudio + ", causa=" + causa + "]";
	}
}
