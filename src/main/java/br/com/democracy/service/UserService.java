package br.com.democracy.service;

import java.util.List;

import br.com.democracy.dto.UserInputDTO;
import br.com.democracy.dto.UserOutputDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.persistence.User;

public interface UserService {

	void registerUser(UserInputDTO userInput) throws ServiceException;

	User getUserByEmail(String userName);

	List<UserOutputDTO> getAwaitingUsers() throws ServiceException;

	void activateUser(Long userId) throws ServiceException;

	void deactivateUser(Long userId) throws ServiceException;

	void registerAdmin(UserInputDTO userInput) throws ServiceException;

	UserOutputDTO getUserDetails(Long userId) throws ServiceException;


}
