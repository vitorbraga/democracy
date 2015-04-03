package br.com.democracy.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.dto.QuestionInputDTO;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.helper.ResultControllerHelper;
import br.com.democracy.helper.ValidationHelper;
import br.com.democracy.messages.Messages;
import br.com.democracy.service.QuestionService;
import br.com.democracy.validation.Validator;

@Resource
@Path("/question")
public class QuestionController {

	@Autowired
	private Result result;

	@Autowired
	private QuestionService questionService;

	@Post
	@Path("/new")
	public void newQuestion(QuestionInputDTO question) {

		try {
			Validator.validate(question);

			questionService.newQuestion(question);
			
			result.redirectTo(AdminController.class).home();
		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}
	
	@Get
	@Path("/activateQuestion")
	public void newQuestion(String questionId) {

		try {

			if(!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			questionService.activateQuestion(ConvertHelper.convertIdFromView(questionId));
			
		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}
}
