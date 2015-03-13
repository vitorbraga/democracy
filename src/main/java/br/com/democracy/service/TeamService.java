package br.com.democracy.service;

import java.util.List;

import br.com.democracy.dto.TeamOutputDTO;

public interface TeamService {
	List<TeamOutputDTO> getTeams();
}
