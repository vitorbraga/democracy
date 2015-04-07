package br.com.democracy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.dto.UserInputDTO;
import br.com.democracy.dto.UserOutputDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.helper.ResultControllerHelper;
import br.com.democracy.helper.ValidationHelper;
import br.com.democracy.messages.Messages;
import br.com.democracy.service.UserService;
import br.com.democracy.validation.Validator;

@Resource
@Path("/user")
public class UserController {

	@Autowired
	private Result result;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;

	/**
	 * método que cadastra um novo usuário esse método se diferencia do
	 * addOrEditUser pois aqui o usuário se auto cadastra, necessitando a
	 * aprovação de um admin
	 * 
	 */
	@Post
	@Path("/registerUser")
	public void registerUser(UserInputDTO user) {

		try {
			Validator.validate(user);

			if (!user.getEmail().equals(user.getEmailConf())) {
				throw new ValidationException(
						Messages.EMAIL_CONFIRMATION_INCORRECT);
			}

			if (!user.getPassword().equals(user.getPasswordConf())) {
				throw new ValidationException(
						Messages.PASSWORD_CONFIRMATION_INCORRECT);
			}

			userService.registerUser(user);

			result.redirectTo(HomeController.class).welcome();

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	/**
	 * Método que adiciona ou edita um novo usuário
	 * 
	 */
	@Post
	@Path("/addOrEditUser")
	public void addOrEditUser() {

	}

	@Get
	@Path("/getAwaitingUsers")
	public void getAwaitingUsers() {

		try {

			List<UserOutputDTO> users = userService.getAwaitingUsers();

			result.include("users", users);

		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Post
	@Path("/activateUser")
	public void activateUser(String userId) {

		try {
			if (!ValidationHelper.isIdFromView(userId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			userService.activateUser(ConvertHelper.convertIdFromView(userId));
		
			ResultControllerHelper.returnResultSuccess(result);
			
		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}

	}

}
