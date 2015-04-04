package br.com.democracy.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.dto.UserInputDTO;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.helper.ResultControllerHelper;
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
	 * método que cadastra um novo usuário
	 * esse método se diferencia do addOrEditUser pois aqui 
	 * o usuário se auto cadastra, necessitando a aprovação de um admin
	 * 
	 */
	@Post
	@Path("/registerUser")
	public void registerUser(UserInputDTO userInput) {
		
		try {
			Validator.validate(userInput);

			userService.registerUser(userInput);
			
			result.redirectTo(HomeController.class).welcome();
		} catch (ValidationException e) {
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
	
	
	
}
