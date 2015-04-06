package br.com.democracy.dto;

import br.com.democracy.messages.Messages;
import br.com.democracy.validation.annotation.Validate;


public class QuestionSearchDTO {

	@Validate(message=Messages.STATUS_FIELD_INVALID, validation="isNumberOrEmpty")
	private String status;
	
	@Validate(message=Messages.PERIOD_FIELD_INVALID, validation="isNumberOrEmpty")
	private String date;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
