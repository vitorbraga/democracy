package br.com.democracy.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.democracy.persistence.FootballPlayer;

public class FootballPlayerOutputDTO {

	private Long id;

	private String name;

	private BigDecimal height;

	private Integer age;

	private Date lastGamePlayed;
	
	private String teamName;
	
	public static FootballPlayerOutputDTO copy(FootballPlayer player) {
		FootballPlayerOutputDTO footballPlayerOutputDTO = new FootballPlayerOutputDTO();

		footballPlayerOutputDTO.setId(player.getId());
		footballPlayerOutputDTO.setName(player.getName());
		footballPlayerOutputDTO.setHeight(player.getHeight());
		footballPlayerOutputDTO.setAge(player.getAge());
		footballPlayerOutputDTO.setLastGamePlayed(player.getLastGamePlayed());
		footballPlayerOutputDTO.setTeamName(player.getTeam().getName());

		return footballPlayerOutputDTO;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public Integer getAge() {
		return age;
	}

	public Date getLastGamePlayed() {
		return lastGamePlayed;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setLastGamePlayed(Date lastGamePlayed) {
		this.lastGamePlayed = lastGamePlayed;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
