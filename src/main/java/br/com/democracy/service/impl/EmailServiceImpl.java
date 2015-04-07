package br.com.democracy.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import br.com.democracy.helper.Constants;
import br.com.democracy.helper.EmailConstant;
import br.com.democracy.persistence.User;
import br.com.democracy.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public void sendPostRegisterEmail(User user) throws AddressException,
			MessagingException {

		String body = EmailConstant.POST_REGISTER_BODY;
		body = body.replace("{VAR_NAME}", user.getName());
		
		genericSendMessage(user.getEmail(),
				EmailConstant.POST_REGISTER_SUBJECT,
				body);
		
		System.out.println("Enviado email de cadastro efetuado.");
	}

	@Override
	public void sendPostApprovalEmail(User user) throws AddressException,
			MessagingException {

		String body = EmailConstant.POST_APPROVAL_BODY;
		body = body.replace("{VAR_NAME}", user.getName());
		
		genericSendMessage(user.getEmail(),
				EmailConstant.POST_APPROVAL_SUBJECT, body);
		
		System.out.println("Enviado email de aprovação de cadastro.");
	}

	public void genericSendMessage(String destination, String subject,
			String body) throws AddressException, MessagingException {

		String from = Constants.GOOGLE_ACCOUNT_USERNAME;

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.host", "smtp.gmail.com");

		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								Constants.GOOGLE_ACCOUNT_USERNAME,
								Constants.GOOGLE_ACCOUNT_PASSWORD);
					}
				});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(destination));
		message.setSubject(subject);
		message.setText(body);

		Transport.send(message);

	}
}
