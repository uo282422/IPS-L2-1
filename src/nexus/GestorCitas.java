package nexus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import logic.Medico;
import logic.Paciente;
import logic.Prescripcion;
import logic.cita.Cita;
import logic.cita.Enum_acudio;
import logic.jornada.Jornada;
import ui.Especialidad;
import util.DataBase;

public class GestorCitas {

	private DataBase bd = null;

	private ArrayList<Cita> listaCitas = new ArrayList<>();
	private ArrayList<Medico> medicos = new ArrayList<Medico>();
	private ArrayList<Especialidad> especialidades = new ArrayList<Especialidad>();

	private int tlfProv;
	private String correoProv;
	private String otrosProv;

	private GestorPacientes gP = new GestorPacientes();

	public GestorCitas() {
		this.bd = new DataBase();
	}

	public void nuevaCita(int idPaciente, String nombre, String fecha,
			String horaE, String horaS, int salaId, boolean urg,
			String motivos) {
		Cita c = new Cita(bd.generarIdCita(), idPaciente, fecha, horaE, horaS,
				urg, salaId, tlfProv, correoProv, otrosProv,
				Enum_acudio.NO_FIGURA, new ArrayList<String>(), motivos);
		listaCitas.add(c);

		bd.crearCita(c, new ArrayList<Medico>(medicos),
				new ArrayList<Especialidad>(especialidades));

		if (urg)
			enviarCorreo(c);

	}

	public ArrayList<Cita> getListaCitas() {
		return listaCitas;
	}

	public void setListaCitas(ArrayList<Cita> listaCitas) {
		this.listaCitas = listaCitas;
	}

	private void enviarCorreo(Cita c) {
		// Al tener una cita urgente se notifica al medico, en este caso lo
		// simuluaremos
		// pr pantalla por email
		System.out.println("Urgente: Enviando correo");
//		String contraseña="2022admin123";
		String contraseña = "tsocimwznyxhgpfg";
		String remitente = "ips2022adm";
		String destinatario = "ips2022med@gmail.com";
		// String contDest="fmtatkgsnwfpqhiu";

		Properties prop = new Properties();

		prop.setProperty("mail.smtp.host", "smtp.gmail.com");// servidor smtp
																// google
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.port", "587");

		prop.setProperty("mail.smtp.user", remitente);// remitente, enviador
														// (antes del
														// @gmail.com)
		prop.setProperty("mail.smtp.clave", contraseña);// clave del que envia
		prop.setProperty("mail.smtp.auth", "true");

		Session sesion = Session.getDefaultInstance(prop);
		sesion.setDebug(true);
		MimeMessage m = new MimeMessage(sesion);
		try {
			m.setFrom(new InternetAddress(remitente));
			m.addRecipient(Message.RecipientType.TO,
					new InternetAddress(destinatario));
			m.setSubject(String.format("Cita urgente %s. Dia %s",
					gP.getPaciente(c.getIdPaciente()).getNombre(),
					c.getFecha()));
			m.setText(String.format(
					"Se ha creado una nueva cita urgente para el paciente %s el dia %s \n"
							+ "La cita se realizará en la sala %s a las %s \n",
					gP.getPaciente(c.getIdPaciente()).getNombre(), c.getFecha(),
					c.getSala(), c.getHoraE()));

			Transport t = sesion.getTransport("smtp");
			t.connect((String) prop.get("mail.smtp.host"),
					(String) prop.get("mail.smtp.user"),
					(String) prop.get("mail.smtp.clave"));
			t.sendMessage(m, m.getAllRecipients());
			t.close();

		} catch (MessagingException e) {
			System.out.println("Algo salio mal");
			e.printStackTrace();
			return;
		}

	}

	public void agregarMedico(Medico medicoNuevo) {

		medicos.add(medicoNuevo);
	}

	public void agregarEspecialidad(Especialidad espNueva) {

		especialidades.add(espNueva);
	}

	public ArrayList<Medico> getMedicosAgregados() {
		return new ArrayList<Medico>(medicos);
	}

	public ArrayList<Especialidad> getEspecialidadesAgregadas() {
		return new ArrayList<Especialidad>(especialidades);
	}

	public void añadirInfoContactoProv(Paciente paciente) {
		this.tlfProv = paciente.getTelefono();
		this.correoProv = paciente.getCorreo();
		this.otrosProv = paciente.getOtrosContactos();

	}

	public void añadirInfoContactoProv(int tlf, String corr, String otro) {
		this.tlfProv = tlf;
		this.correoProv = corr;
		this.otrosProv = otro;

	}

	public List<Cita> cargarCitasOrdenadas(String idMedico, String fecha) {

		List<Cita> citas = bd.getCitasPorMedicoYFecha(idMedico, fecha);

		Collections.sort(citas,
				(o1, o2) -> (Integer.parseInt(o1.getHoraE().split(":")[0])
						- Integer.parseInt(o2.getHoraE().split(":")[0]) == 0
								? Integer.parseInt(o1.getHoraE().split(":")[1])
										- Integer.parseInt(
												o2.getHoraE().split(":")[1])
								: Integer.parseInt(o1.getHoraE().split(":")[0])
										- Integer.parseInt(
												o2.getHoraE().split(":")[0])));

		// debug
		for (Cita c : citas) {
			System.out.println(c);
		}

		return citas;
	}

	public List<Cita> cargarCitasOrdenadas(int idPaciente) {
		List<Cita> citas = bd.cargarCitasPorPaciente(idPaciente);

		Collections.sort(citas, (o1, o2) -> (Integer
				.parseInt(o1.getFecha().split("/")[2])
				- Integer.parseInt(o2.getFecha().split("/")[2]) == 0
						? (Integer.parseInt(o1.getFecha().split("/")[1])
								- Integer.parseInt(
										o2.getFecha().split("/")[1]) == 0
												? (Integer
														.parseInt(o1.getFecha()
																.split("/")[0])
														- Integer.parseInt(o2
																.getFecha()
																.split("/")[0]) == 0
																		? (Integer
																				.parseInt(
																						o1.getHoraE()
																								.split(":")[0])
																				- Integer
																						.parseInt(
																								o2.getHoraE()
																										.split(":")[0]) == 0
																												? Integer
																														.parseInt(
																																o1.getHoraE()
																																		.split(":")[1])
																														- Integer
																																.parseInt(
																																		o2.getHoraE()
																																				.split(":")[1])
																												: Integer
																														.parseInt(
																																o1.getHoraE()
																																		.split(":")[0])
																														- Integer
																																.parseInt(
																																		o2.getHoraE()
																																				.split(":")[0]))
																		: Integer
																				.parseInt(
																						o1.getFecha()
																								.split("/")[0])
																				- Integer
																						.parseInt(
																								o2.getFecha()
																										.split("/")[0]))
												: Integer
														.parseInt(o1.getFecha()
																.split("/")[1])
														- Integer.parseInt(o2
																.getFecha()
																.split("/")[1]))
						: Integer.parseInt(o1.getFecha().split("/")[2])
								- Integer.parseInt(
										o2.getFecha().split("/")[2])));

		// debug
		for (Cita c : citas) {
			System.out.println(c);
		}

		return citas;
	}

	public List<Medico> cargarMedicos(int idCita) {
		return bd.cargarMedicosPorCita(idCita);
	}

	public int getTlfProv() {
		return tlfProv;
	}

	public String getCorreoProv() {
		return correoProv;
	}

	public String getOtrosProv() {
		return otrosProv;
	}

	public boolean comprobarCitaEnJornada(String fecha, String horaE,
			String horaS, List<Medico> medicos) {
		boolean valido = true;
		Date fechaCandidato = fechaToDate(fecha);
		Date horaECandidato = horaToDate(horaE);
		Date horaSCandidato = horaToDate(horaS);
		for (Medico m : medicos) {
			List<Jornada> jornadaDeMedico = bd.getJornadasDeMedico(m.getId());
			for (Jornada j : jornadaDeMedico) {

				Date fechaInicialJornada = j.getCalendario().getDiaInicio();
				Date fechaFinalJornada = j.getCalendario().getDiaFin();
//				System.out.println("id del medico"+m.getId());
//				System.out.println("fecha a evaluar"+fechaCandidato.toString());
//				System.out.println("inicio de jornada"+fechaInicialJornada.toString());
//				System.out.println("fin de jornada"+fechaFinalJornada.toString());

				if (fechaCandidato.after(fechaInicialJornada)
						&& fechaCandidato.before(fechaFinalJornada)) {
					// si la fecha candidato esta dentro de la jornada es valido
					// de momento
//					System.out.println("Fecha en jornada");
				} else
					valido = false;

				Date horaInicialJornada = horaToDate(j.getHoraComienzo());
				Date horaFinalJornada = horaToDate(j.getHoraFinal());
				if (horaECandidato.after(horaInicialJornada)
						&& horaECandidato.before(horaFinalJornada)
						&& horaSCandidato.after(horaInicialJornada)
						&& horaSCandidato.before(horaFinalJornada)) {
					// si la inicial candidato es despues de la inicial y antes
					// de la final

					// si la final candidato es despues de la inicial y antes de
					// la final
				} else
					valido = false;

				if (valido)
					break;
			}

		}
		return valido;
	}

	public boolean comprobarCitasEnHorario(String fecha, String horaE,
			String horaS, ArrayList<Medico> medicos) {
		boolean valido = true;
		Date horaECandidato = horaToDate(horaE);
		Date horaSCandidato = horaToDate(horaS);
		for (Medico m : medicos) {
			List<Cita> citasDeMedico = bd.getCitasPorMedicoYFecha(m.getId(),
					fecha);
			for (Cita c : citasDeMedico) {
				Date horaInicialCitaPuesta = horaToDate(c.getHoraE());
				Date horaFinalCitaPuesta = horaToDate(c.getHoraS());
				if ((horaECandidato.after(horaFinalCitaPuesta) || horaSCandidato
						.before(horaInicialCitaPuesta)) == false)// si no se
																	// cumple
					valido = false;
			}
		}
		return valido;

	}

	/*
	 * <<<<<<< HEAD Pasa del string guardado en bd a un objeto del tipo fecha
	 * Formato: DD/MM/YYYY 0 1 2 ======= Pasa del string guardado en bd a un
	 * objeto del tipo fecha Formato: DD/MM/YYYY 0 1 2 >>>>>>>
	 * refs/remotes/origin/master
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

	public Cita getCita(String idCita) {
		return bd.getCita(idCita);
	}

	public void guardarCita(Cita c) {
		bd.guardarCita(c);
	}

	/**
	 * Método que llama a la base de datos para actualizar las causas y si el
	 * paciente acudió de una cita.
	 * 
	 * @param c Cita que se quiere actualizar.
	 */
	public void actualizarCita(Cita c) {
		bd.actualizarCita(c);
	}

	public List<String> cargarTodasCausas() {
		// TODO Auto-generated method stub
		return bd.cargarTodasCausas();
	}

	public void nuevaCausa(String causa) {
		bd.nuevaCausa(causa);

	}

	public void limpiarMedicos() {
		medicos.removeAll(medicos);

	}

	public void limpiarEspecialidades() {
		especialidades.removeAll(especialidades);

	}

	public List<Prescripcion> cargarPrescripcionesSinParam() {
		return bd.cargarPrescripciones();
	}

}
