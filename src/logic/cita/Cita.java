package logic.cita;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Cita {

	private int idCita;
	private int idPaciente;
	private String nombrePaciente;
	private String fecha;
	private String horaE;
	private String horaS;
	private boolean urgente;
	private int sala;
	private Enum_acudio acudio;
	private List<String> causas;

	private int telefonoCita;
	private String correoCita;
	private String otrosCita;

	public Cita(int id, int pacienteId, String fecha, String horaI,
			String horaF, boolean urgente, int salaId, int telefono,
			String correo, String otros, Enum_acudio acudio,
			List<String> causas) {
		this.idCita = id;
		setIdPaciente(pacienteId);
		setFecha(fecha);
		setHoraE(horaI);
		setHoraS(horaF);
		this.urgente = urgente;
		this.sala = salaId;
		this.telefonoCita = telefono;
		this.correoCita = correo;
		this.otrosCita = otros;
		this.causas = causas;
	}

	private void setHoraS(String hs) {
		this.horaS = hs;

	}

	private void setHoraE(String he) {
		this.horaE = he;

	}

	private void setFecha(String f) {
		this.fecha = f;
	}

	public String getDia() {
		return fecha;
	}

	public int getSala() {
		return sala;
	}

	public String getHoraE() {
		return horaE;
	}

	public String getHoraS() {
		return horaS;
	}

	public void setNombre(String str) {
		nombrePaciente = str;

	}

	public void setIdPaciente(int i) {
		this.idPaciente = i;
	}

	public int getTelefonoCita() {
		return telefonoCita;
	}

	public void setTelefonoCita(int telefonoCita) {
		this.telefonoCita = telefonoCita;
	}

	public String getCorreoCita() {
		return correoCita;
	}

	public void setCorreoCita(String correoCita) {
		this.correoCita = correoCita;
	}

	public String getOtrosCita() {
		return otrosCita;
	}

	public void setOtrosCita(String otrosCita) {
		this.otrosCita = otrosCita;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	/*
	 * Pasa del string guardado en bd a un objeto del tipo fecha Formato:
	 * DD/MM/YYYY 0 1 2
	 */
	public Date fechaToDate(String fecha) {
		String[] sep = fecha.split("/");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sep[0]));
		c.set(Calendar.MONTH, Integer.parseInt(sep[1]) - 1);
		c.set(Calendar.YEAR, Integer.parseInt(sep[2]));

		Date f = c.getTime();
		return f;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public Enum_acudio isAcudio() {
		return acudio;
	}

	public void setAcudio(Enum_acudio acudio) {
		this.acudio = acudio;
	}

	public String getFecha() {
		return fecha;
	}

	@Override
	public String toString() {
		return "Cita [idCita=" + idCita + ", idPaciente=" + idPaciente
				+ ", nombrePaciente=" + nombrePaciente + ", fecha=" + fecha
				+ ", horaE=" + horaE + ", horaS=" + horaS + ", urgente="
				+ urgente + ", sala=" + sala + ", acudio=" + acudio
				+ ", telefonoCita=" + telefonoCita + ", correoCita="
				+ correoCita + ", otrosCita=" + otrosCita + ", causaCita="
				+ causas + "]";
	}

	/*
	 * 
	 * HH:MM 0 1
	 */
	public Date horaToDate(String e) {
		String[] sep = e.split(":");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sep[0]));
		c.set(Calendar.MINUTE, Integer.parseInt(sep[1]));
		Date h = c.getTime();
		return h;
	}

	public int getIdCita() {
		return idCita;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public boolean isUrgente() {
		return urgente;
	}

	public void setUrgente(boolean u) {
		urgente = u;
	}

	public void setSala(int num) {
		this.sala = num;
	}

	public boolean urgente() {
		return urgente;
	}

	public String getHora() {
		return horaE.toString();
	}

//	/*
//	 * Pasa del string guardado en bd a un objeto del tipo fecha Formato:
//	 * DD/MM/YYYY 0 1 2
//	 */
//	public void setFecha(String fecha) {
//		String[] sep = fecha.split("/");
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sep[0]));
//		c.set(Calendar.MONTH, Integer.parseInt(sep[1]) - 1);
//		c.set(Calendar.YEAR, Integer.parseInt(sep[2]));
//
//		this.fecha = c.getTime();
//	}

//	/*
//	 * HH:MM 0 1
//	 */
//	public void setHoraE(String e) {
//		String[] sep = e.split(":");
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sep[0]));
//		c.set(Calendar.MINUTE, Integer.parseInt(sep[1]));
//		this.horaE = c.getTime();
//	}

//	public void setHoraS(String s) {
//		String[] sep = s.split(":");
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sep[0]));
//		c.set(Calendar.MINUTE, Integer.parseInt(sep[1]));
//		this.horaS = c.getTime();
//	}

	public String parseFecha() {
		return fecha.toString();
	}

	public List<String> getCausas() {
		return new ArrayList<String>(causas);
	}

	public void setCausas(List<String> causas) {
		this.causas = causas;
	}

	public void addCausa(String c) {
		causas.add(c);
	}

	public void deleteCausa(String c) {
		causas.remove(c);
	}

}
