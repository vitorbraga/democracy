package br.com.democracy.session;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.democracy.persistence.User;
import br.com.democracy.security.CustomUserDetails;
import br.com.democracy.service.UserSessionService;

@Component
@SessionScoped
public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserSessionService userSessionService;

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public static UserSession copy(User user) {
		
		UserSession userSession = new UserSession();
		userSession.setUserId(user.getId());
		
		return userSession;
	}
	
	public CustomUserDetails getUserDetails() {
		try {
			return userSessionService.getUserDetails();
		} catch (Exception e) {
			return null;
		}
	}

}
