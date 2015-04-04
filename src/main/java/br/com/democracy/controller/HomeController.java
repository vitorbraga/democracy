package br.com.democracy.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
@Path("/home")
public class HomeController {
	
	@Autowired
	private Result result;

	@Autowired
	private HttpServletRequest request;
	
	@Get
	@Path("/")
	public void home() {
		
	}
	
	@Get
	@Path("")
	public void home2() {
		result.redirectTo(HomeController.class).home();
	}
	
	@Get
	@Path("/welcome")
	public void welcome() {
		
	}
	
	@Get
	@Path("/searchQuestion")
	public void searchQuestion() {
	}
	
	
	@Get
	@Path("/searchUser")
	public void searchUser() {
	}
	
	
}
