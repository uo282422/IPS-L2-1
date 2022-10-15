package logic;

public class Cita {

	private int idCita;
	private int idPaciente;
	private String nombrePaciente;
	private String fecha;
	private String horaE;
	private String horaS;
	private boolean urgente;
	private int sala;
	private int telefonoCita;
	private String correoCita;
	private String otrosCita;

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

	public boolean isAcudio() {
		return acudio;
	}

	public void setAcudio(boolean acudio) {
		this.acudio = acudio;
	}

	public String getCausaCita() {
		return causaCita;
	}

	public void setCausaCita(String causaCita) {
		this.causaCita = causaCita;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	private boolean acudio;
	private String causaCita;

	public void setIdCita(int idCita) {
		this.idCita = idCita;
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

	public Cita(int id, int pacienteId, String fecha, String horaI,
			String horaF, boolean urgente, int salaId, int telefono,
			String correo, String otros, boolean acudio, String causa) {
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
		this.acudio = acudio;
		this.causaCita = causa;
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
		int n = (int) (Math.random() * 1000 + 1);
		return n;
	}

	public void setNombre(String str) {
		nombrePaciente = str;

	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setHoraE(String horaE) {
		this.horaE = horaE;
	}

	public void setHoraS(String horaS) {
		this.horaS = horaS;
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

	public void acudio(boolean acudio) {
		this.acudio = acudio;
	}

	public boolean urgente() {
		return urgente;
	}

	public int getSala() {
		return sala;
	}

	public String getHora() {
		return horaE.toString();
	}

	public String getFecha() {
		return fecha.toString();
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

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public String parseFecha() {
		return fecha.toString();
	}

}
