package br.com.democracy.dao;

import java.util.List;

import br.com.democracy.dto.FootballPlayerInputDTO;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.persistence.FootballPlayer;

public interface FootballPlayerDAO extends GenericDAO<FootballPlayer> {

	List<FootballPlayer> getFootballPlayers(FootballPlayerInputDTO playerDTO)
			throws ValidationException;

	List<Object[]> getAllPlayers();

	List<Object[]> getAllPlayersSummary();

}
