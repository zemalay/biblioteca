package edu.uepb.web.biblioteca.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.uepb.web.biblioteca.model.Divida;
import edu.uepb.web.biblioteca.model.Reserva;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class Email {
	private static final String emailOrigem = "devgcoder@gmail.com";
	private static final String senha = "developer12";
	private String emailDestino;
	private Reserva reserva;
	private Divida divida;

	private Session emailConfig() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		// Criar autenticacao
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailOrigem, senha);
			}
		};
		Session session = Session.getInstance(props, auth);
		return session;
	}

	public boolean sendNotificacaoReserva() {
		Session session = emailConfig();

		String subject = "Reserva do Item";
		String body = "";
		
		

		try {
			MimeMessage msg = new MimeMessage(session);
			// set headers da mensagem
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino, false));
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	public String getEmailDestino() {
		return emailDestino;
	}

	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Divida getDivida() {
		return divida;
	}

	public void setDivida(Divida divida) {
		this.divida = divida;
	}

}
