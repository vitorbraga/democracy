package br.com.democracy.dto;

import br.com.democracy.validation.annotation.Validate;

public class HoliDayDTO {
	
	@Validate(message= "Descrição inválida", validation= "isNameCompany")
	private String description;
	
	@Validate(message= "Data inválida", validation= "isDateShort")
	private String date;
	
	
	public static HoliDayDTO copy(Object[] holiDay) {
		HoliDayDTO holiDayDTO = new HoliDayDTO();
		holiDayDTO.setDescription(holiDay[3].toString());
		holiDayDTO.setDate(holiDay[4].toString());
		
		return holiDayDTO;
	}

	public String getDescription() {
		return description;
	}

	public String getDate() {
		return date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
