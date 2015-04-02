package br.com.democracy.dto;

import java.util.List;

import br.com.democracy.messages.Messages;
import br.com.democracy.validation.annotation.Validate;

public class QuestionInputDTO {

	@Validate(message=Messages.QUESTION_FIELD_INVALID, validation="isQuestionOrAnswer")
	private String question;
	
	private List<String> answers;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

}
