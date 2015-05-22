package br.com.democracy.dto;

import br.com.democracy.messages.Messages;
import br.com.democracy.validation.annotation.Validate;

public class AnswerInputDTO {

	@Validate(message=Messages.ANSWER_FIELD_INVALID, validation="isQuestionOrAnswerOrEmpty")
	private String answer;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
