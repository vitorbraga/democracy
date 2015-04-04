package br.com.democracy.dto;

import br.com.democracy.messages.Messages;
import br.com.democracy.validation.annotation.Validate;

public class UserInputDTO {

	@Validate(message=Messages.NAME_INVALID, validation="isNamePerson")
	private String name;
	
	@Validate(message=Messages.EMAIL_INVALID, validation="isEmail")
	private String email;
	
	@Validate(message=Messages.PASSWORD_INVALID, validation="isPasswordUser")
	private String password;
	
	private String academicRegister;
	
	private SelectBoxDTO gender;

	private Integer status;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcademicRegister() {
		return academicRegister;
	}

	public void setAcademicRegister(String academicRegister) {
		this.academicRegister = academicRegister;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SelectBoxDTO getGender() {
		return gender;
	}

	public void setGender(SelectBoxDTO gender) {
		this.gender = gender;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
