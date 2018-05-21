package edu.uepb.web.biblioteca.utils;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.uepb.web.biblioteca.dao.DividaDAOImpl;
import edu.uepb.web.biblioteca.dao.EmprestimoDAOImpl;
import edu.uepb.web.biblioteca.model.Divida;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Reserva;

/**
 * Classe Email, responsavel para enviar o email
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
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

	/**
	 * Enviar notificacao que aluno fez reserva no item
	 * 
	 * @param reserva
	 * @return
	 */
	public boolean sendNotificacaoReserva(Reserva reserva) {
		Session session = emailConfig();

		String subject = "Reserva do Item";
		String alunoText = "Aluno: " + reserva.getAluno().getNome() + "\n" + "Matricula: "
				+ reserva.getAluno().getMatricula();
		String itemText = "\nReservou o item: " + reserva.getItem().getTitulo() + "\n" + "Codigo: "
				+ reserva.getItem().getId();
		String dataPegar = "\n Data previsao para pegar: " + reserva.getDataPegar();
		String body = alunoText + itemText + dataPegar;

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

	/**
	 * Enviar notificacao ao aluno que o item reservado esta disponivel
	 * 
	 * @param item
	 * @return
	 */
	public boolean sendNotificacaoDevolucao(Item item) {
		Session session = emailConfig();

		String subject = "O item reservado esta disponivel";
		String body = "O item com titulo: " + item.getTitulo();

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

	/**
	 * Enviar Relatorios no ultimo periodo 
	 * 
	 * @return
	 */
	public boolean sendRelatorio() {
		Session session = emailConfig();
		EmprestimoDAOImpl emprestimoDAOImpl = new EmprestimoDAOImpl();
		DividaDAOImpl dividaDAOImpl = new DividaDAOImpl();
		List<Emprestimo> listaEmprestimo = emprestimoDAOImpl.getLista();
		List<Divida> listaDivida = dividaDAOImpl.getLista();
		String subject = "O item reservado esta disponivel";
		String body = "Lista Alunos que ainda nao devolvem Item: \n" + listaEmprestimo
				+ "Lista Alunos que ainda nao pagam suas dividas: \n" + listaDivida;

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
