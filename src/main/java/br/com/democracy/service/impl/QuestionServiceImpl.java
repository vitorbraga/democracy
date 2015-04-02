package br.com.democracy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.dao.QuestionDAO;
import br.com.democracy.dto.QuestionInputDTO;
import br.com.democracy.persistence.Question;
import br.com.democracy.persistence.enums.QuestionStatusEnum;
import br.com.democracy.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDAO;
	
	@Override
	@Transactional(readOnly = false)
	public void newQuestion(QuestionInputDTO questionDTO) {

		/* A pergunta é cadastrada com status inativa. Ela deve ser ativada 
		 * manualmente pelo Admin */
		Question question = QuestionInputDTO.copy(questionDTO);
		
		questionDAO.saveOrUpdate(question);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void activateQuestion(Long questionId) {

		Question question = questionDAO.getById(questionId);

		question.setStatus(QuestionStatusEnum.ACTIVE.id());
		
		questionDAO.saveOrUpdate(question);
		
	}
	
}
