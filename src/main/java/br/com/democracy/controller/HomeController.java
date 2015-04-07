package br.com.democracy.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.helper.ResultControllerHelper;
import br.com.democracy.session.UserSession;

@Resource
@Path("/")
public class HomeController {
	
	@Autowired
	private Result result;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserSession userSession;
	
	@Get
	@Path("/home")
	public void home() {
		
	}
	
	@Get
	@Path("/")
	public void welcome() {
		
	}
	
	@Get
	@Path("/home/")
	public void home2() {
		result.redirectTo(HomeController.class).home();
	}
	
	
	@Get
	@Path("/searchQuestion")
	public void searchQuestion() {
	}
	
	
	@Get
	@Path("/searchUser")
	public void searchUser() {
	}
	
	@Get
	@Path("/register")
	public void register() {
	}
	
	@Get
	@Path("/login")
	public void login() {
	}

	@Get
	@Path("/loginError")
	public void loginError() {
	}
	
	@Get
	@Path("/doLogout")
	public void doLogout() {
	}
	
	@Get
	@Path("/loginSuccess")
	public void loginSuccess() {
		try {
			// if (userType.equals(UserTypeEnum.ADMIN.id())) {
			// ResultControllerHelper.returnResultWithoutRoot(result,
			// "/admin/panel");
			// } else {
			// ResultControllerHelper.returnResultWithoutRoot(result,
			// "/organizer/events");
			// }

		} catch (Exception e) {
			ResultControllerHelper.returnResultWithoutRoot(result, "/login");
		}
	}
}
