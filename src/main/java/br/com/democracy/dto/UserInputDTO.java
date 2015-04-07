package br.com.democracy.dto;

import java.util.Date;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

import br.com.democracy.helper.DateHelper;
import br.com.democracy.messages.Messages;
import br.com.democracy.persistence.User;
import br.com.democracy.validation.annotation.Validate;

public class UserInputDTO {

	@Validate(message=Messages.NAME_INVALID, validation="isNamePerson")
	private String name;
	
	@Validate(message=Messages.EMAIL_INVALID, validation="isEmail")
	private String email;
	
	@Validate(message=Messages.EMAIL_CONFIRMATION_INVALID, validation="isEmail")
	private String emailConf;
	
	@Validate(message=Messages.PASSWORD_INVALID, validation="isPasswordUser")
	private String password;
	
	@Validate(message=Messages.PASSWORD_CONFIRMATION_INVALID, validation="isPasswordUser")
	private String passwordConf;
	
	@Validate(message=Messages.GENDER_FIELD_INVALID, validation="isGender")
	private String gender;
	
	private String academicRegister;
	
	private SelectBoxDTO genderSelectBox;

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

	public String getEmailConf() {
		return emailConf;
	}

	public void setEmailConf(String emailConf) {
		this.emailConf = emailConf;
	}

	public String getPasswordConf() {
		return passwordConf;
	}

	public void setPasswordConf(String passwordConf) {
		this.passwordConf = passwordConf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public SelectBoxDTO getGenderSelectBox() {
		return genderSelectBox;
	}

	public void setGenderSelectBox(SelectBoxDTO genderSelectBox) {
		this.genderSelectBox = genderSelectBox;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public static User createUser(UserInputDTO userInput) {
		
		if(userInput != null) {
			Date now = DateHelper.now(); 
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			
			User user = new User();
			user.setName(userInput.getName());
			user.setEmail(userInput.getEmail());
			user.setPassword(encoder.encode(userInput.getPassword()));
			user.setGender(Integer.parseInt(userInput.getGender()));
			user.setRegDate(now);
			user.setRegUser("admin@email.com");
			user.setUpdated(now);

			return user;
		}
		
		return null;
	}
}
