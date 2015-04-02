package br.com.democracy.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.service.QuestionService;

@Resource
@Path("/question")
public class QuestionController {
	
	@Autowired
	private Result result;
	
	@Autowired
	private QuestionService questionService;
	
	@Post
	@Path("/new")
	public void newQuestion() {
		
	}
}
