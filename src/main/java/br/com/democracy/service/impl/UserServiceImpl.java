package br.com.democracy.service.impl;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.dao.UserDAO;
import br.com.democracy.dto.UserInputDTO;
import br.com.democracy.dto.UserOutputDTO;
import br.com.democracy.dto.UserSearchDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.DateHelper;
import br.com.democracy.messages.Messages;
import br.com.democracy.persistence.User;
import br.com.democracy.persistence.enums.UserStatusEnum;
import br.com.democracy.persistence.enums.UserTypeEnum;
import br.com.democracy.security.CustomUserDetails;
import br.com.democracy.service.EmailService;
import br.com.democracy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private EmailService emailService;

	@Override
	@Transactional(readOnly = false)
	public void registerUser(UserInputDTO userInput) throws ServiceException {

		User emailUser = userDAO.getUserByEmail(userInput.getEmail());

		if (emailUser == null) {
			User user = UserInputDTO.createUser(userInput);

			user.setStatus(UserStatusEnum.AWAITING_APPROVAL.id());
			user.setType(UserTypeEnum.NORMAL.id());

			user = userDAO.saveOrUpdate(user);

			class SendPostRegiserEmailTask implements Runnable {

				private User user;

				SendPostRegiserEmailTask(User user) {
					this.user = user;
				}

				public void run() {
					try {
						/* Envia email após cadastro */
						emailService.sendPostRegisterEmail(user);

					} catch (AddressException e) {
						e.printStackTrace();
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}

			/* Inicia thread que envia email */
			Thread thread = new Thread(new SendPostRegiserEmailTask(user));
			thread.start();

		} else {
			throw new ServiceException(Messages.EMAIL_ALREADY_REGISTERED);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void registerAdmin(UserInputDTO userInput) throws ServiceException {

		User emailUser = userDAO.getUserByEmail(userInput.getEmail());

		if (emailUser == null) {
			User user = UserInputDTO.createUser(userInput);

			user.setStatus(UserStatusEnum.ACTIVE.id());
			user.setType(UserTypeEnum.ADMIN.id());

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

	@Override
	@Transactional(readOnly = true)
	public List<UserOutputDTO> getAwaitingUsers() throws ServiceException {

		List<User> users = userDAO.getAwaitingNormalUsers();

		return UserOutputDTO.copy(users);
	}

	@Override
	@Transactional(readOnly = false)
	public void activateUser(Long userId) throws ServiceException {

		User user = userDAO.getById(userId);

		if (user != null) {

			user.setStatus(UserStatusEnum.ACTIVE.id());
			user.setUpdated(DateHelper.now());
			user.setDateActive(DateHelper.now());

			user = userDAO.saveOrUpdate(user);

			class SendPostApprovalEmailTask implements Runnable {

				private User user;

				SendPostApprovalEmailTask(User user) {
					this.user = user;
				}

				public void run() {
					try {
						/* Envia email após aprovação do admin */
						emailService.sendPostApprovalEmail(user);

					} catch (AddressException e) {
						e.printStackTrace();
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}

			/* Inicia thread que envia email */
			Thread thread = new Thread(new SendPostApprovalEmailTask(user));
			thread.start();

		} else {
			throw new ServiceException(Messages.USER_NOT_FOUND);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deactivateUser(Long userId) throws ServiceException {

		User user = userDAO.getById(userId);

		if (user != null) {

			user.setStatus(UserStatusEnum.INACTIVE.id());
			user.setUpdated(DateHelper.now());
			user.setDateActive(null);

			userDAO.saveOrUpdate(user);

		} else {
			throw new ServiceException(Messages.USER_NOT_FOUND);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserOutputDTO getUserDetails() throws ServiceException {
		CustomUserDetails userSession = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		User user = userDAO.getById(userSession.getId());

		if (user == null) {
			throw new ServiceException(Messages.USER_NOT_FOUND);
		}
		return UserOutputDTO.copy(user);
	}

	@Override
	@Transactional(readOnly = false)
	public void editUser(UserInputDTO userInput) throws ServiceException {
		CustomUserDetails userSession = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		User user = userDAO.getById(userSession.getId());
		if (user == null) {
			throw new ServiceException(Messages.USER_NOT_FOUND);
		}
		if (!encoder.matches(userInput.getPassword(), user.getPassword()))	{
			throw new ServiceException(Messages.PASSWORD_INVALID);
		}
		

		User emailUser = userDAO.getUserByEmail(userInput.getEmail());

		if (emailUser == null) {
			updateUser(user, userInput);

			user = userDAO.saveOrUpdate(user);

		} else {
			if (emailUser.getEmail() != user.getEmail())
				throw new ServiceException(Messages.EMAIL_ALREADY_REGISTERED);
			
			updateUser(user, userInput);
			user = userDAO.saveOrUpdate(user);
			
		}

	}
	
	private void updateUser(User user, UserInputDTO userInput)	{
		
		if(userInput != null) {
			Date now = DateHelper.now(); 
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			
			user.setName(userInput.getName());
			user.setEmail(userInput.getEmail());
			user.setPassword(encoder.encode(userInput.getPassword()));
			user.setGender(Integer.parseInt(userInput.getGender()));
			user.setAcademicRegister(userInput.getAcademicRegister());
			user.setUpdated(now);

		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserOutputDTO> searchUser(UserSearchDTO search)
			throws ServiceException {

		List<User> users = userDAO.searchUser(search);

		return UserOutputDTO.copy(users);
	}
}
