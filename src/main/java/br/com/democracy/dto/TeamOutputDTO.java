package br.com.democracy.dto;

import br.com.democracy.persistence.Team;


public class TeamOutputDTO {

	private Long id;

	private String name;
	
	private String badge;
	
	public static TeamOutputDTO copy(Team team) {
		TeamOutputDTO dto = new TeamOutputDTO();
		dto.setId(team.getId());
		dto.setName(team.getName());
		dto.setBadge(team.getBadge());
		return dto;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}
}
