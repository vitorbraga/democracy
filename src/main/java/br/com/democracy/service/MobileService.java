package br.com.democracy.service;

import javax.servlet.http.HttpSession;

import br.com.democracy.exception.ServiceException;

public interface MobileService {

	String authenticate(String email, String password, HttpSession session)
			throws ServiceException;

}
