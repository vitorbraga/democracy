package br.com.democracy.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import br.com.democracy.persistence.User;

public interface EmailService {

	void sendPostRegisterEmail(User user) throws AddressException,
			MessagingException;

	void sendPostApprovalEmail(User user) throws AddressException,
			MessagingException;

}
