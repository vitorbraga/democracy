package br.com.democracy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.dao.AnswerDAO;
import br.com.democracy.dao.QuestionDAO;
import br.com.democracy.dto.AnswerEditDTO;
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
import br.com.democracy.persistence.Question;
import br.com.democracy.persistence.enums.QuestionStatusEnum;
import br.com.democracy.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	private AnswerDAO answerDAO;

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
	public void activateQuestion(Long questionId) {

		Date now = DateHelper.now();
		Question question = questionDAO.getById(questionId);

		question.setStatus(QuestionStatusEnum.ACTIVE.id());
		question.setDateActivated(now);
		question.setUpdated(now);

		questionDAO.saveOrUpdate(question);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void deactivateQuestion(Long questionId) {

		Date now = DateHelper.now();
		Question question = questionDAO.getById(questionId);

		question.setStatus(QuestionStatusEnum.INACTIVE.id());
		question.setDateActivated(null);
		question.setUpdated(now);

		questionDAO.saveOrUpdate(question);
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

}
