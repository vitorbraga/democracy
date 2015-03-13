package br.com.democracy.dto;

import br.com.democracy.validation.annotation.Validate;

public class FootballPlayerInputDTO {
	
	@Validate(message="Nome inválido", validation="isNameCompanyPartialOrEmpty")
	private String name;

	@Validate(message="Altura inválida", validation="isDecimalOrEmpty")
	private String height;

	@Validate(message="Idade inválida", validation="isNumberOrEmpty")
	private String age;

	@Validate(message="Último jogo inválido", validation="isDateShortOrEmpty")
	private String lastGamePlayed;
	
	@Validate(message="Nome do time inválido", validation="isNameCompanyPartialOrEmpty")
	private String teamName;

	public String getName() {
		return name;
	}

	public String getHeight() {
		return height;
	}

	public String getAge() {
		return age;
	}

	public String getLastGamePlayed() {
		return lastGamePlayed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setLastGamePlayed(String lastGamePlayed) {
		this.lastGamePlayed = lastGamePlayed;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}
