package br.com.democracy.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.dao.UserDAO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.persistence.User;
import br.com.democracy.persistence.enums.UserStatusEnum;
import br.com.democracy.persistence.enums.UserTypeEnum;
import br.com.democracy.service.MobileService;

@Service
public class MobileServiceImpl implements MobileService {

	@Autowired
	private UserDAO userDAO; 
	
	@Override
	@Transactional(readOnly = true)
	public String authenticate(String email, String password, HttpSession session) throws ServiceException {
		
		User user = userDAO.getUserByEmail(email);
		
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		if(user != null) {
			if (!user.getType().equals(UserTypeEnum.NORMAL.id())
					|| !user.getStatus().equals(UserStatusEnum.ACTIVE.id())) {
				throw new ServiceException("Usuário sem permissão.");
			}
			
			if(encoder.matches(password, user.getPassword())) {
				session.setAttribute("user", user);
				// create token
				String token = ConvertHelper.convertIdToView(user.getId());
				user.setToken(token);
				userDAO.saveOrUpdate(user);
				
				return token;
			} else {
				throw new ServiceException("Senha incorreta");
			}
		} else {
			throw new ServiceException("Usuario não cadastrado");
		}
		
	}
	
}
