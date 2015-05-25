package br.com.democracy.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.dto.CommentOutputDTO;
import br.com.democracy.dto.PartialResultsDTO;
import br.com.democracy.dto.QuestionAvailableOutputDTO;
import br.com.democracy.dto.ResponseDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.helper.ResultControllerHelper;
import br.com.democracy.helper.ValidationHelper;
import br.com.democracy.messages.Messages;
import br.com.democracy.service.MobileService;

@Resource
@Path("/mobile")
public class MobileController {

	@Autowired
	private Result result;

	@Autowired
	private MobileService mobileService;

	@Autowired
	private HttpServletRequest request;

	@Post
	@Path("/authenticate")
	public void authenticate(String email, String password) {

		try {
			if (!ValidationHelper.isEmail(email)) {
				throw new ValidationException(Messages.EMAIL_INVALID);
			}

			if (!ValidationHelper.isPasswordUser(password)) {
				throw new ValidationException(Messages.PASSWORD_INVALID);
			}

			String token = mobileService.authenticate(email, password,
					request.getSession());

			ResultControllerHelper.returnResultWithoutRoot(result,
					new ResponseDTO(true, token));

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ParseException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}

	}

	@Get
	@Path("/getAvailableQuestions")
	public void getAvailableQuestions(String token) {

		try {
			if (ValidationHelper.isEmptyOrVoid(token)) {
				throw new ValidationException(Messages.TOKEN_INVALID);
			}

			List<QuestionAvailableOutputDTO> questions = mobileService
					.getAvailableQuestions(token);

			ResultControllerHelper.returnResultWithoutRoot(result, questions);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Post
	@Path("/answerQuestion")
	public void answerQuestion(String token, String questionId, String answerId) {

		try {
			if (ValidationHelper.isEmptyOrVoid(token)) {
				throw new ValidationException(Messages.TOKEN_INVALID);
			}
			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			if (!ValidationHelper.isIdFromView(answerId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			// chamar mobileServie que vai chamar questionService
			mobileService.answerQuestion(token,
					ConvertHelper.convertIdFromView(questionId),
					ConvertHelper.convertIdFromView(answerId));

			ResultControllerHelper.returnResultSuccess(result);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}
	
	@Post
	@Path("/answerDiscursiveQuestion")
	public void answerDiscursiveQuestion(String token, String questionId, String answer) {

		try {
			if (ValidationHelper.isEmptyOrVoid(token)) {
				throw new ValidationException(Messages.TOKEN_INVALID);
			}
			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			if (!ValidationHelper.isQuestionOrAnswer(answer)) {
				throw new ValidationException(Messages.ANSWER_FIELD_INVALID);
			}

			// chamar mobileServie que vai chamar questionService
			mobileService.answerDiscursiveQuestion(token,
					ConvertHelper.convertIdFromView(questionId), answer);

			ResultControllerHelper.returnResultSuccess(result);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Post
	@Path("/makeComment")
	public void makeComment(String token, String questionId, String comment) {

		try {
			if (ValidationHelper.isEmptyOrVoid(token)) {
				throw new ValidationException(Messages.TOKEN_INVALID);
			}

			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			if (!ValidationHelper.isComment(comment)) {
				throw new ValidationException(Messages.COMMENT_INVALID);
			}

			mobileService.makeComment(token,
					ConvertHelper.convertIdFromView(questionId), comment);

			ResultControllerHelper.returnResultSuccess(result);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/getPartialResults")
	public void partialResultsModal(String token, String questionId) {

		try {

			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			PartialResultsDTO partialResults = mobileService.getPartialResults(
					token, ConvertHelper.convertIdFromView(questionId));

			ResultControllerHelper.returnResultWithoutRoot(result,
					partialResults);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}

	@Get
	@Path("/getQuestionComments")
	public void getQuestionComments(String token, String questionId) {

		try {

			if (!ValidationHelper.isIdFromView(questionId)) {
				throw new ValidationException(Messages.ID_INVALID);
			}

			List<CommentOutputDTO> comments = mobileService
					.getQuestionComments(token,
							ConvertHelper.convertIdFromView(questionId));

			ResultControllerHelper.returnResultWithoutRoot(result, comments);

		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
	}
}
