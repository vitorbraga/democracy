package br.com.democracy.service;

import org.springframework.security.core.context.SecurityContext;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.security.CustomUserDetails;

public interface UserSessionService {

	CustomUserDetails getUserDetails() throws ServiceException;

	SecurityContext getSecurityContext();

}
