package br.com.democracy.service.impl;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.messages.Messages;
import br.com.democracy.security.CustomUserDetails;
import br.com.democracy.service.UserSessionService;



@Service
public class UserSessionServiceImpl implements UserSessionService {

	@Override
	@Transactional(readOnly = true)
	public CustomUserDetails getUserDetails() throws ServiceException {
		final SecurityContext sec = SecurityContextHolder.getContext();

		if (null != sec.getAuthentication()
				&& sec.getAuthentication().getPrincipal() instanceof CustomUserDetails) {
			final CustomUserDetails userDetails = (CustomUserDetails) sec
					.getAuthentication().getPrincipal();
			return userDetails;
		}

		throw new ServiceException(Messages.USER_MUST_LOGIN);
	}

	@Override
	@Transactional(readOnly = true)
	public SecurityContext getSecurityContext() {
		return SecurityContextHolder.getContext();
	}

}
