package br.com.democracy.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.persistence.User;

public class UserOutputDTO {

	private String id;

	private String name;

	private String email;

	private String dateRegistered;
	
	private Long genderId;
	
	private String academicRegister;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(String dateRegistered) {
		this.dateRegistered = dateRegistered;
	}
	
	public Long getGenderId() {
		return genderId;
	}

	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}
	
	public String getAcademicRegister() {
		return academicRegister;
	}

	public void setAcademicRegister(String academicRegister) {
		this.academicRegister = academicRegister;
	}

	public static List<UserOutputDTO> copy(List<User> users)
			throws ServiceException {

		if (users != null) {
			List<UserOutputDTO> dtos = new ArrayList<UserOutputDTO>();
			for (User user : users) {
				UserOutputDTO dto = new UserOutputDTO();

				dto.setId(ConvertHelper.convertIdToView(user.getId()));
				dto.setName(user.getName());
				dto.setEmail(user.getEmail());
				dto.setDateRegistered(ConvertHelper.dateToView(user
						.getRegDate()));

				dtos.add(dto);
			}

			return dtos;
		}

		return null;
	}

	public static UserOutputDTO copy(User user) throws ServiceException {
		if (user != null) {
			UserOutputDTO dto = new UserOutputDTO();

			dto.setId(ConvertHelper.convertIdToView(user.getId()));
			dto.setName(user.getName());
			dto.setEmail(user.getEmail());
			dto.setDateRegistered(ConvertHelper.dateToView(user.getRegDate()));
			dto.setGenderId(user.getGender().longValue());
			if (null != user.getAcademicRegister())
				dto.setAcademicRegister(user.getAcademicRegister());
			return dto;

		}
		return null;

	}


	
}
