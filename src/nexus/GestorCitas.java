package nexus;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import logic.Cita;
import util.DataBase;

public class GestorCitas {
	
	private ArrayList<Cita> listaCitas=new ArrayList<>();

	public GestorCitas(DataBase bd) {
		
	}

	public void nuevaCita(int idPaciente, String nombre, String fecha, String horaE, String horaS, int sala, boolean urg) {
		Cita c=new Cita(idPaciente, fecha,horaE, horaS, sala, urg);
		listaCitas.add(c);
		if(urg)enviarCorreo(c);
	}

	private void enviarCorreo(Cita c) {
		// Al tener una cita urgente se notifica al medico, en este caso lo simuluaremos pr pantalla por email
		System.out.println("Urgente: Enviando correo");
//		String contraseña="2022admin123";
		String contraseña="tsocimwznyxhgpfg";
		String remitente="ips2022adm";
		String destinatario="ips2022med@gmail.com";
		//String contDest="fmtatkgsnwfpqhiu";
		
		
		Properties prop=new Properties();
		
		
		prop.setProperty("mail.smtp.host", "smtp.gmail.com");//servidor smtp google
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.port","587");
		
		prop.setProperty("mail.smtp.user", remitente);//remitente, enviador (antes del @gmail.com)
		prop.setProperty("mail.smtp.clave", contraseña);//clave del que envia
		prop.setProperty("mail.smtp.auth", "true");
		
		


		
		Session sesion = Session.getDefaultInstance(prop);
		sesion.setDebug(true);
		MimeMessage m=new MimeMessage(sesion);
		try {
			m.setFrom(new InternetAddress(remitente));
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			m.setSubject("Prueba n1");
			m.setText("Texto");
			
			Transport t=sesion.getTransport("smtp");
			t.connect((String)prop.get("mail.smtp.host"),(String)prop.get("mail.smtp.user"),(String)prop.get("mail.smtp.clave"));
			t.sendMessage(m, m.getAllRecipients());
			t.close();
			
		}catch(MessagingException e) {
			System.out.println("Algo salio mal");
			e.printStackTrace();
			return;
		}
		
	}
	
}
