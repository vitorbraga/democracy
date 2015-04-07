package br.com.democracy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.dao.UserDAO;
import br.com.democracy.dto.UserInputDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.messages.Messages;
import br.com.democracy.persistence.User;
import br.com.democracy.persistence.enums.UserStatusEnum;
import br.com.democracy.persistence.enums.UserTypeEnum;
import br.com.democracy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional(readOnly = false)
	public void registerUser(UserInputDTO userInput) throws ServiceException {

		User emailUser = userDAO.getUserByEmail(userInput.getEmail());

		if (emailUser == null) {
			User user = UserInputDTO.createUser(userInput);
			// FIXME inicialmente sera AWAITING_APPROVAL
			user.setStatus(UserStatusEnum.ACTIVE.id());
			user.setType(UserTypeEnum.NORMAL.id());

			userDAO.saveOrUpdate(user);

		} else {
			throw new ServiceException(Messages.EMAIL_ALREADY_REGISTERED);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByEmail(final String userName) {

		User user = new User();
		user.setEmail(userName);

		return userDAO.findUniqueByExample(user);
	}

}
