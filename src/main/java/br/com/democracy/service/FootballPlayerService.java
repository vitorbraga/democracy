package br.com.democracy.service;

import java.util.List;

import br.com.democracy.dto.FootballPlayerInputDTO;
import br.com.democracy.dto.FootballPlayerOutputDTO;
import br.com.democracy.dto.SelectBoxDTO;
import br.com.democracy.exception.ValidationException;

public interface FootballPlayerService {

	List<FootballPlayerOutputDTO> getFootballPlayers(
			FootballPlayerInputDTO playerDTO) throws ValidationException;

	List<SelectBoxDTO> getAllPlayers();

	List<SelectBoxDTO> getAllPlayersSummary();

	List<FootballPlayerOutputDTO> getPlayersByName(String name);

}
