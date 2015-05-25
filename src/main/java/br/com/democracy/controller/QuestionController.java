package br.com.democracy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.dto.CommentOutputDTO;
import br.com.democracy.dto.PartialResultsDTO;
import br.com.democracy.dto.QuestionAvailableOutputDTO;
import br.com.democracy.dto.QuestionEditDTO;
import br.com.democracy.dto.QuestionInputDTO;
import br.com.democracy.dto.QuestionOutputDTO;
import br.com.democracy.dto.QuestionSearchDTO;
import br.com.democracy.exception.ServiceException;
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

			ResultControllerHelper.returnResultSuccess(result);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Post
	@Path("/edit")
	public void editQuestion(QuestionEditDTO edit) {

		try {
			Validator.validate(edit);

			questionService.editQuestion(edit);

			// result.redirectTo(AdminController.class).home();
			ResultControllerHelper.returnResultSuccess(result);
		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/activateQuestion")
	public void activateQuestion(String questionId) {

		try {

			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			questionService.activateQuestion(ConvertHelper
					.convertIdFromView(questionId));

			ResultControllerHelper.returnResultSuccess(result);
		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/deactivateQuestion")
	public void deactivateQuestion(String questionId) {

		try {

			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			questionService.deactivateQuestion(ConvertHelper
					.convertIdFromView(questionId));

			ResultControllerHelper.returnResultSuccess(result);
		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/search")
	public void search(QuestionSearchDTO search) {

		try {

			Validator.validate(search);

			List<QuestionOutputDTO> questions = questionService
					.searchQuestion(search, true);

			result.include("questions", questions);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/searchHome")
	public void searchHome(QuestionSearchDTO search) {

		try {

			Validator.validate(search);

			List<QuestionOutputDTO> questions = questionService
					.searchQuestion(search, false);

			result.include("questions", questions);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/questionDetails")
	public void questionDetails(String questionId) {

		try {
			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			QuestionOutputDTO question = questionService
					.getQuestionDetails(ConvertHelper
							.convertIdFromView(questionId));
			result.include("question", question);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/questionAnswerModal")
	public void questionAnswerModal(String questionId) {

		try {
			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			QuestionAvailableOutputDTO question = questionService.getQuestionAnswerData(
					ConvertHelper.convertIdFromView(questionId), false, null);
			result.include("question", question);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/getAvailableQuestions")
	public void getAvailableQuestions() {

		try {

			List<QuestionAvailableOutputDTO> questions = questionService
					.getAvailableQuestions(false, null);

			result.include("questions", questions);

		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/getActiveQuestions")
	public void getActiveQuestions() {

		try {

			List<QuestionOutputDTO> questions = questionService
					.getActiveQuestions();

			result.include("questions", questions);

		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Post
	@Path("/answerQuestion")
	public void answerQuestion(String questionId, String answerId) {

		try {
			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			if (!ValidationHelper.isIdFromView(answerId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			questionService.answerQuestion(
					ConvertHelper.convertIdFromView(questionId),
					ConvertHelper.convertIdFromView(answerId), false, null);

			ResultControllerHelper.returnResultSuccess(result);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Post
	@Path("/answerDiscursiveQuestion")
	public void answerDiscursiveQuestion(String questionId, String answer) {

		try {
			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			if (!ValidationHelper.isComment(answer)) {
				throw new ValidationException(Messages.ANSWER_FIELD_INVALID);
			}

			questionService.answerDiscursiveQuestion(
					ConvertHelper.convertIdFromView(questionId), answer, false,
					null);

			ResultControllerHelper.returnResultSuccess(result);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Post
	@Path("/makeComment")
	public void makeComment(String questionId, String comment) {

		try {

			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			if (!ValidationHelper.isComment(comment)) {
				throw new ValidationException(Messages.COMMENT_INVALID);
			}

			questionService.makeComment(
					ConvertHelper.convertIdFromView(questionId), comment,
					false, null);

			result.redirectTo(QuestionController.class).commentBox(questionId);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/commentBox")
	public void commentBox(String questionId) {

		try {

			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			List<CommentOutputDTO> comments = questionService.getComments(
					ConvertHelper.convertIdFromView(questionId), false, null);

			result.include("questionId", questionId);
			result.include("comments", comments);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/partialResultsModal")
	public void partialResultsModal(String questionId) {

		try {

			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			PartialResultsDTO partialResults = questionService
					.getPartialResults(
							ConvertHelper.convertIdFromView(questionId), false,
							null);

			result.include("partialResults", partialResults);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}
}
