package br.com.democracy.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import br.com.democracy.persistence.enums.UserTypeEnum;

public class AuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {

		CustomUserDetails userSession = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		if(Integer.parseInt(userSession.getUserType()) == UserTypeEnum.ADMIN.id()) {
			setDefaultTargetUrl("/admin");
		} else {
			setDefaultTargetUrl("/home");
		}
				
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
