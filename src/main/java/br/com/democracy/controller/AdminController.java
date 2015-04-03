package br.com.democracy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
@Path("/admin")
public class AdminController {

	
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
	@Path("/newAdmin")
	public void newAdmin() {
	}
	
	
}
