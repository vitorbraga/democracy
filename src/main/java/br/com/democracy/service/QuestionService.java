package br.com.democracy.service;

import br.com.democracy.dto.QuestionInputDTO;

public interface QuestionService {

	void newQuestion(QuestionInputDTO question);

	void activateQuestion(Long questionId);

}
