package br.com.democracy.dto;

import java.util.List;

import br.com.democracy.messages.Messages;
import br.com.democracy.validation.annotation.Validate;
import br.com.democracy.validation.annotation.ValidateCollection;

public class QuestionEditDTO {

	@Validate(message=Messages.ID_INVALID, validation="isIdFromView")
	private String id;
	
	@Validate(message=Messages.QUESTION_FIELD_INVALID, validation="isQuestionOrAnswer")
	private String question;
	
	@Validate(message=Messages.STATUS_FIELD_INVALID, validation="isNumber")
	private String status;
	
	@ValidateCollection(recursive = true, nullable = false)
	private List<AnswerInputDTO> answers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AnswerInputDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerInputDTO> answers) {
		this.answers = answers;
	}
	
}
