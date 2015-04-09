package br.com.democracy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.dao.AnswerDAO;
import br.com.democracy.dao.CommentDAO;
import br.com.democracy.dao.QuestionDAO;
import br.com.democracy.dao.UserAnswerDAO;
import br.com.democracy.dao.UserDAO;
import br.com.democracy.dao.UserQuestionDAO;
import br.com.democracy.dto.AnswerEditDTO;
import br.com.democracy.dto.QuestionAvailableOutputDTO;
import br.com.democracy.dto.QuestionEditDTO;
import br.com.democracy.dto.QuestionInputDTO;
import br.com.democracy.dto.QuestionOutputDTO;
import br.com.democracy.dto.QuestionSearchDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.helper.DateHelper;
import br.com.democracy.messages.Messages;
import br.com.democracy.persistence.Answer;
import br.com.democracy.persistence.Comment;
import br.com.democracy.persistence.Question;
import br.com.democracy.persistence.User;
import br.com.democracy.persistence.UserAnswer;
import br.com.democracy.persistence.UserQuestion;
import br.com.democracy.persistence.enums.QuestionStatusEnum;
import br.com.democracy.security.CustomUserDetails;
import br.com.democracy.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	private AnswerDAO answerDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserAnswerDAO userAnswerDAO;
	
	@Autowired
	private UserQuestionDAO userQuestionDAO;

	@Override
	@Transactional(readOnly = false)
	public void newQuestion(QuestionInputDTO questionDTO) {

		/*
		 * A pergunta é cadastrada com status inativa. Ela deve ser ativada
		 * manualmente pelo Admin
		 */
		Question question = QuestionInputDTO.copy(questionDTO);

		questionDAO.saveOrUpdate(question);

	}

	@Override
	@Transactional(readOnly = false)
	public void editQuestion(QuestionEditDTO edit) throws ValidationException {

		/*
		 * A pergunta é cadastrada com status inativa. Ela deve ser ativada
		 * manualmente pelo Admin
		 */
		Date now = DateHelper.now();
		Question question = questionDAO.getById(ConvertHelper
				.convertIdFromView(edit.getId()));

		question.setQuestion(edit.getQuestion());
		if (question.getStatus().equals(QuestionStatusEnum.INACTIVE.id())
				&& Integer.parseInt(edit.getStatus()) == QuestionStatusEnum.ACTIVE
						.id()) {
			question.setDateActivated(now);
		}
		question.setStatus(Integer.parseInt(edit.getStatus()));
		question.setUpdated(now);

		for (AnswerEditDTO answerEdit : edit.getAnswers()) {
			Answer answer = answerDAO.getById(ConvertHelper
					.convertIdFromView(answerEdit.getId()));
			answer.setAnswer(answerEdit.getAnswer());
			answer.setUpdated(now);

			answerDAO.saveOrUpdate(answer);
		}

		questionDAO.saveOrUpdate(question);

	}

	@Override
	@Transactional(readOnly = false)
	public void activateQuestion(Long questionId) throws ServiceException {

		Question question = questionDAO.getById(questionId);

		if (question == null) {
			throw new ServiceException(Messages.QUESTION_NOT_FOUND);
		} else {

			if (question.getStatus().equals(QuestionStatusEnum.ACTIVE.id())) {
				throw new ServiceException(Messages.QUESTION_ALREADY_ACTIVATED);
			}

			Date now = DateHelper.now();

			question.setStatus(QuestionStatusEnum.ACTIVE.id());
			question.setDateActivated(now);
			question.setUpdated(now);

			questionDAO.saveOrUpdate(question);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deactivateQuestion(Long questionId) throws ServiceException {

		Question question = questionDAO.getById(questionId);

		if (question == null) {
			throw new ServiceException(Messages.QUESTION_NOT_FOUND);
		} else {

			if (question.getStatus().equals(QuestionStatusEnum.INACTIVE.id())) {
				throw new ServiceException(
						Messages.QUESTION_ALREADY_DEACTIVATED);
			}

			Date now = DateHelper.now();

			question.setStatus(QuestionStatusEnum.INACTIVE.id());
			question.setDateActivated(null);
			question.setUpdated(now);

			questionDAO.saveOrUpdate(question);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionOutputDTO> searchQuestion(QuestionSearchDTO search)
			throws ServiceException {

		List<Question> questions = questionDAO.searchQuestion(search);

		return QuestionOutputDTO.copyAll(questions);
	}

	@Override
	@Transactional(readOnly = true)
	public QuestionOutputDTO getQuestionDetails(Long questionId)
			throws ServiceException {

		Question question = questionDAO.getById(questionId);

		if (question == null) {
			throw new ServiceException(Messages.QUESTION_NOT_FOUND);
		}

		return QuestionOutputDTO.copy(question);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionAvailableOutputDTO> getAvailableQuestions()
			throws ServiceException {

		CustomUserDetails userSession = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		User user = userDAO.getById(userSession.getId());

		if (user == null) {
			throw new ServiceException(Messages.USER_NOT_FOUND);
		}

		List<Question> questions = questionDAO.getAvailableQuestions();

		List<UserQuestion> userQuestions = userQuestionDAO.getByUser(user.getId());
		
		return QuestionAvailableOutputDTO.copyAll(questions, userQuestions);
	}

	@Override
	@Transactional(readOnly = false)
	public void answerQuestion(Long questionId, Long answerId)
			throws ServiceException {

		CustomUserDetails userSession = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		User user = userDAO.getById(userSession.getId());
		if (user == null) {
			throw new ServiceException(Messages.USER_NOT_FOUND);
		}
		
		Question question = questionDAO.getById(questionId);
		if (question == null) {
			throw new ServiceException(Messages.QUESTION_NOT_FOUND);
		}
		
		Answer answer = answerDAO.getById(answerId);
		if (answer == null) {
			throw new ServiceException(Messages.ANSWER_NOT_FOUND);
		}
		
		Date now = DateHelper.now();
		
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setUser(user);
		userAnswer.setAnswer(answer);
		userAnswer.setCreated(now);
		userAnswer.setUpdated(now);
		
		userAnswerDAO.saveOrUpdate(userAnswer);
		
		UserQuestion userQuestion = new UserQuestion();
		userQuestion.setUser(user);
		userQuestion.setQuestion(question);
		userQuestion.setCreated(now);
		userQuestion.setUpdated(now);
		userQuestion.setAnswerId(answerId);
		
		userQuestionDAO.saveOrUpdate(userQuestion);
	}

	@Override
	@Transactional(readOnly = false)
	public void makeComment(Long questionId, String comment)
			throws ServiceException {

		CustomUserDetails userSession = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		User user = userDAO.getById(userSession.getId());
		if (user == null) {
			throw new ServiceException(Messages.USER_NOT_FOUND);
		}

		Question question = questionDAO.getById(questionId);
		if (question == null) {
			throw new ServiceException(Messages.QUESTION_NOT_FOUND);
		}

		Comment newComment = new Comment();
		newComment.setComment(comment);

		newComment.setQuestion(question);
		newComment.setUser(user);
		newComment.setRegDate(DateHelper.now());
		newComment.setUpdated(DateHelper.now());
		newComment.setRegUser("admin@email.com");

		newComment = commentDAO.saveOrUpdate(newComment);

		if (user.getComments() == null) {
			user.setComments(new ArrayList<Comment>());
		}
		user.getComments().add(newComment);

		if (question.getComments() == null) {
			question.setComments(new ArrayList<Comment>());
		}
		question.getComments().add(newComment);

		questionDAO.saveOrUpdate(question);
	}
}
