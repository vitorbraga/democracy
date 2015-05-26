package br.com.democracy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.dto.UserOutputDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ResultControllerHelper;
import br.com.democracy.service.UserService;

@Resource
@Path("/admin")
public class AdminController {

	
	@Autowired
	private Result result;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userService;
	
	@Get
	@Path("/")
	public void home() {
		
	}
	
	@Get
	@Path("")
	public void home2() {
		result.redirectTo(AdminController.class).home();
	}
	
	@Get
	@Path("/searchQuestion")
	public void searchQuestion() {
	}
	
	@Get
	@Path("/newQuestion")
	public void newQuestion() {
	}
	
	@Get
	@Path("/searchUser")
	public void searchUser() {
	}
	
	@Get
	@Path("/registerAdmin")
	public void registerAdmin() {
	}
	
	@Get
	@Path("/awaitingUsers")
	public void awaitingUsers() {
		
		
		try {
			List<UserOutputDTO> users = userService.getAwaitingUsers();
	
			result.include("users", users);
			
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}
	
	@Get
	@Path("/userDetails")
	public void userDetails()	{
		try	{
			
			UserOutputDTO user = userService.getUserDetails();
			result.include("user", user);
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}
	
	
}
