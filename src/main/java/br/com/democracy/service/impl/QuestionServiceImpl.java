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
import br.com.democracy.dao.DiscursiveAnswerDAO;
import br.com.democracy.dao.QuestionDAO;
import br.com.democracy.dao.UserAnswerDAO;
import br.com.democracy.dao.UserDAO;
import br.com.democracy.dao.UserDiscursiveAnswerDAO;
import br.com.democracy.dao.UserQuestionDAO;
import br.com.democracy.dto.AnswerEditDTO;
import br.com.democracy.dto.AnswerOutputDTO;
import br.com.democracy.dto.CommentOutputDTO;
import br.com.democracy.dto.DiscursiveAnswerOutputDTO;
import br.com.democracy.dto.PartialResultsDTO;
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
import br.com.democracy.persistence.DiscursiveAnswer;
import br.com.democracy.persistence.Question;
import br.com.democracy.persistence.User;
import br.com.democracy.persistence.UserAnswer;
import br.com.democracy.persistence.UserDiscursiveAnswer;
import br.com.democracy.persistence.UserQuestion;
import br.com.democracy.persistence.enums.QuestionStatusEnum;
import br.com.democracy.persistence.enums.QuestionTypeEnum;
import br.com.democracy.security.CustomUserDetails;
import br.com.democracy.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	private AnswerDAO answerDAO;

	@Autowired
	private DiscursiveAnswerDAO discursiveAnswerDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private UserAnswerDAO userAnswerDAO;

	@Autowired
	private UserDiscursiveAnswerDAO userDiscursiveAnswerDAO;

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
	public List<QuestionOutputDTO> searchQuestion(QuestionSearchDTO search, boolean isAdmin)
			throws ServiceException {

		List<Question> questions = questionDAO.searchQuestion(search, isAdmin);

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
	public QuestionAvailableOutputDTO getQuestionAnswerData(Long questionId,
			boolean isMobile, String token) throws ServiceException {

		User user = getUser(isMobile, token);

		Question question = questionDAO.getById(questionId);

		if (question == null) {
			throw new ServiceException(Messages.QUESTION_NOT_FOUND);
		}

		List<UserQuestion> userQuestions = userQuestionDAO.getByUser(user
				.getId());

		return QuestionAvailableOutputDTO.copy(question, userQuestions);
	}

	@Transactional(readOnly = true)
	private User getUser(boolean isMobile, String token)
			throws ServiceException {

		User user = null;
		if (!isMobile) {
			CustomUserDetails userSession = (CustomUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();

			user = userDAO.getById(userSession.getId());

			if (user == null) {
				throw new ServiceException(Messages.USER_NOT_FOUND);
			}
		} else {
			user = userDAO.getUserByToken(token);

			if (user == null) {
				throw new ServiceException(Messages.USER_NOT_FOUND);
			}
		}

		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionAvailableOutputDTO> getAvailableQuestions(
			boolean isMobile, String token) throws ServiceException {

		User user = getUser(isMobile, token);

		List<Question> questions = questionDAO.getAvailableQuestions();

		List<UserQuestion> userQuestions = userQuestionDAO.getByUser(user
				.getId());

		return QuestionAvailableOutputDTO.copyAll(questions, userQuestions);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionOutputDTO> getActiveQuestions() throws ServiceException {

		List<Question> questions = questionDAO.getAvailableQuestions();

		return QuestionOutputDTO.copyAll(questions);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void answerQuestion(Long questionId, Long answerId,
			boolean isMobile, String token) throws ServiceException {

		User user = getUser(isMobile, token);

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
	public void answerDiscursiveQuestion(Long questionId, String answer,
			boolean isMobile, String token) throws ServiceException {

		User user = getUser(isMobile, token);

		Question question = questionDAO.getById(questionId);
		if (question == null) {
			throw new ServiceException(Messages.QUESTION_NOT_FOUND);
		}
		Date now = DateHelper.now();

		DiscursiveAnswer discursiveAnswer = new DiscursiveAnswer();
		discursiveAnswer.setAnswer(answer);
		discursiveAnswer.setQuestion(question);
		discursiveAnswer.setRegDate(now);
		discursiveAnswer.setRegUser("ADMIN");
		discursiveAnswer.setUpdated(now);

		discursiveAnswer = discursiveAnswerDAO.saveOrUpdate(discursiveAnswer);

		UserDiscursiveAnswer userDiscursiveAnswer = new UserDiscursiveAnswer();
		userDiscursiveAnswer.setUser(user);
		userDiscursiveAnswer.setDiscursiveAnswer(discursiveAnswer);
		userDiscursiveAnswer.setCreated(now);
		userDiscursiveAnswer.setUpdated(now);

		userDiscursiveAnswerDAO.saveOrUpdate(userDiscursiveAnswer);

		UserQuestion userQuestion = new UserQuestion();
		userQuestion.setUser(user);
		userQuestion.setQuestion(question);
		userQuestion.setCreated(now);
		userQuestion.setUpdated(now);
		userQuestion.setDiscursiveAnswer(discursiveAnswer.getAnswer());

		userQuestionDAO.saveOrUpdate(userQuestion);
	}

	@Override
	@Transactional(readOnly = false)
	public void makeComment(Long questionId, String comment, boolean isMobile,
			String token) throws ServiceException {

		User user = getUser(isMobile, token);

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

	}

	@Override
	@Transactional(readOnly = true)
	public List<CommentOutputDTO> getComments(Long questionId,
			boolean isMobile, String token) throws ServiceException {

		@SuppressWarnings("unused")
		User user = getUser(isMobile, token);

		Question question = questionDAO.getById(questionId);
		if (question == null) {
			throw new ServiceException(Messages.QUESTION_NOT_FOUND);
		}

		List<CommentOutputDTO> comments = CommentOutputDTO.copyAll(question
				.getComments());

		return comments;
	}

	@Override
	@Transactional(readOnly = true)
	public PartialResultsDTO getPartialResults(Long questionId,
			boolean isMobile, String token) throws ServiceException {

		User user = getUser(isMobile, token);

		Question question = questionDAO.getById(questionId);
		if (question == null) {
			throw new ServiceException(Messages.QUESTION_NOT_FOUND);
		}

		List<UserQuestion> userQuestions = userQuestionDAO
				.getByQuestion(questionId);

		PartialResultsDTO dto = null;
		if (question.getType().equals(QuestionTypeEnum.MULTIPLE_CHOICES.id())) {
			dto = generateResults(userQuestions);
		} else {
			dto = generateResultsDiscursive(userQuestions, user.getId());
		}

		return dto;
	}

	private PartialResultsDTO generateResults(List<UserQuestion> userQuestions)
			throws ServiceException {

		int aux;

		PartialResultsDTO dto = new PartialResultsDTO();
		dto.setTotal(userQuestions.size());
		dto.setType(QuestionTypeEnum.MULTIPLE_CHOICES.id());
		dto.setAnswers(new ArrayList<AnswerOutputDTO>());

		if (userQuestions != null) {
			for (UserQuestion uq : userQuestions) {
				aux = containsAnswer(dto.getAnswers(),
						ConvertHelper.convertIdToView(uq.getAnswerId()));
				if (aux == -1) {

					Answer answer = answerDAO.getById(uq.getAnswerId());
					AnswerOutputDTO answerDTO = AnswerOutputDTO.copy(answer);
					answerDTO.setChosenTimes(1);

					dto.getAnswers().add(answerDTO);
				} else {
					dto.getAnswers()
							.get(aux)
							.setChosenTimes(
									dto.getAnswers().get(aux).getChosenTimes() + 1);
				}
			}
		}

		return dto;
	}

	private int containsAnswer(List<AnswerOutputDTO> answers, String answerId) {

		int i = 0;
		for (AnswerOutputDTO dto : answers) {
			if (dto.getId().equals(answerId)) {
				return i;
			}
			i++;
		}

		return -1;
	}

	private PartialResultsDTO generateResultsDiscursive(
			List<UserQuestion> userQuestions, Long userId)
			throws ServiceException {

		PartialResultsDTO dto = new PartialResultsDTO();
		dto.setType(QuestionTypeEnum.DISCURSIVE.id());
		dto.setTotal(userQuestions.size());
		dto.setDiscursiveAnswers(new ArrayList<DiscursiveAnswerOutputDTO>());

		if (userQuestions != null) {
			for (UserQuestion uq : userQuestions) {
				if (uq.getUser().getId().equals(userId)) {
					DiscursiveAnswerOutputDTO discursiveDTO = new DiscursiveAnswerOutputDTO();
					discursiveDTO.setAnswer(uq.getDiscursiveAnswer());
					discursiveDTO.setDate(ConvertHelper.dateToViewSlash(uq
							.getCreated()));

					dto.getDiscursiveAnswers().add(discursiveDTO);
				}
			}
		}

		return dto;
	}
}
