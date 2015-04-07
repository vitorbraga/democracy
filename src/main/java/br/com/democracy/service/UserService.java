package br.com.democracy.service;

import br.com.democracy.dto.UserInputDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.persistence.User;

public interface UserService {

	void registerUser(UserInputDTO userInput) throws ServiceException;

	User getUserByEmail(String userName);

}
