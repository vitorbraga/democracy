package br.com.democracy.dto;

import br.com.democracy.messages.Messages;
import br.com.democracy.validation.annotation.Validate;

public class UserSearchDTO {

	@Validate(message=Messages.STATUS_FIELD_INVALID, validation="isNumberOrEmpty")
	private String status;
	
	@Validate(message=Messages.NAME_INVALID, validation="isNamePersonOrEmpty")
	private String name;
	
	@Validate(message=Messages.EMAIL_INVALID, validation="isEmailOrEmpty")
	private String email;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
