package nexus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import logic.Cita;
import logic.Jornada;
import logic.Medico;
import logic.Paciente;
import util.DataBase;

public class GestorCitas {

	private DataBase bd = null;

	private ArrayList<Cita> listaCitas = new ArrayList<>();
	private ArrayList<Medico> medicos = new ArrayList<Medico>();

	private int tlfProv;
	private String correoProv;
	private String otrosProv;

	public GestorCitas() {
		this.bd = new DataBase();
	}

	public void nuevaCita(int idPaciente, String nombre, String fecha,
			String horaE, String horaS, int salaId, boolean urg) {
		Cita c = new Cita(bd.generarIdCita(), idPaciente, fecha, horaE, horaS,
				urg, salaId, tlfProv, correoProv, otrosProv, false, "");
		listaCitas.add(c);
		// System.out.println(c.getIdCita()+" "+c.getIdPaciente()+" "+fecha+"
		// "+horaE+" "+horaS+" "+urg+" "+salaId+" "+tlfProv+" "+correoProv+"
		// "+otrosProv+" "+false+" "+ "");

		bd.crearCita(c, new ArrayList<Medico>(medicos));

		if (urg)
			enviarCorreo(c);

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
			m.setSubject("Prueba n1");
			m.setText("Texto");

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

	public boolean agregarMedico(Medico medicoNuevo) {
		for (Medico m : medicos) {
			if (m.getId().equals(medicoNuevo.getId()))
				return true;
		}
		medicos.add(medicoNuevo);
		return false;
	}

	public ArrayList<Medico> getMedicosAgregados() {
		return new ArrayList<Medico>(medicos);
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
				Date fechaInicialJornada = fechaToDate(j.getDiaInicio());
				Date fechaFinalJornada = fechaToDate(j.getDiaFinal());
//				System.out.println("id del medico"+m.getId());
//				System.out.println("fecha a evaluar"+fechaCandidato.toString());
//				System.out.println("inicio de jornada"+fechaInicialJornada.toString());
//				System.out.println("fin de jornada"+fechaFinalJornada.toString());
				if (fechaCandidato.after(fechaInicialJornada)
						&& fechaCandidato.before(fechaFinalJornada)) {
					// si la fecha candidato esta dentro de la jornada es valido
					// de momento

				} else
					valido = false;

				Date horaInicialJornada = horaToDate(j.getHoraComienzo());
				Date horaFinalJornada = horaToDate(j.getHoraFinal());
//				System.out.println("hora inicial candidato"+horaECandidato);
//				System.out.println("hora final candidato"+horaSCandidato);
//				System.out.println("hora inicial jornada"+ horaInicialJornada);
//				System.out.println("hora final jornada"+ horaFinalJornada);
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

			}
		}
		return valido;
	}

	public boolean comprobarCitasEnHorario(String fecha, String horaE,
			String horaS, ArrayList<Medico> medicos) {
//		Date horaECandidato=horaToDate(horaE);
//		Date horaSCandidato=horaToDate(horaS);
//		for(Medico m :medicos) {
//			List<Cita>citasDeMedico=bd.getCitasPorMedicoYFecha(m.getId(), fecha);
//			for(Cita c : citasDeMedico) {
//				Date horaInicialCitaPuesta=horaToDate(c.getHoraE());
//				Date horaFinalCitaPuesta=horaToDate(c.getHoraS());
//				if()
//			}
//		}
		return true;

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

}
