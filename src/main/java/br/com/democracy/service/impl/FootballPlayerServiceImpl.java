package br.com.democracy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.dao.FootballPlayerDAO;
import br.com.democracy.dto.FootballPlayerInputDTO;
import br.com.democracy.dto.FootballPlayerOutputDTO;
import br.com.democracy.dto.ListDTO;
import br.com.democracy.dto.SelectBoxDTO;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.persistence.FootballPlayer;
import br.com.democracy.service.FootballPlayerService;

@Service
public class FootballPlayerServiceImpl implements FootballPlayerService {
	
	@Autowired
	private FootballPlayerDAO footballPlayerDAO;

	@Override
	@Transactional(readOnly = true)
	public List<FootballPlayerOutputDTO> getFootballPlayers(
			FootballPlayerInputDTO playerDTO) throws ValidationException {
		
		// buscar players no banco
		List<FootballPlayer> players = footballPlayerDAO
				.getFootballPlayers(playerDTO);
		
		// copiar pro DTO
		List<FootballPlayerOutputDTO> returnList = new ArrayList<FootballPlayerOutputDTO>();
		for (FootballPlayer player : players) {
			returnList.add(FootballPlayerOutputDTO.copy(player));
		}
		
		// retornar a lista
		return returnList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<FootballPlayerOutputDTO> getPlayersByName(String name) {
		// criar exemplo
		FootballPlayer example = new FootballPlayer();
		example.setName(name);
		
		// buscar players no banco
		ListDTO<FootballPlayer> players = footballPlayerDAO
				.findByExample(example, 0, 100);

		// copiar pro DTO
		List<FootballPlayerOutputDTO> returnList = new ArrayList<FootballPlayerOutputDTO>();
		for (FootballPlayer player : players.getList()) {
			returnList.add(FootballPlayerOutputDTO.copy(player));
		}

		// retornar a lista
		return returnList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SelectBoxDTO> getAllPlayers() {
		// buscar todos os players no dao
		List<Object[]> players = footballPlayerDAO.getAllPlayers();
		
		// copia os dados
		List<SelectBoxDTO> returnList = new ArrayList<SelectBoxDTO>();
		for (Object[] object : players) {
			returnList.add(SelectBoxDTO.copyPlayer(object));
		}
		
		// retorna lista
		return returnList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SelectBoxDTO> getAllPlayersSummary() {
		// buscar todos os players no dao
		List<Object[]> players = footballPlayerDAO.getAllPlayersSummary();
		
		// copia os dados
		List<SelectBoxDTO> returnList = new ArrayList<SelectBoxDTO>();
		for (Object[] object : players) {
			returnList.add(SelectBoxDTO.copyPlayerSummary(object));
		}
		
		// retorna lista
		return returnList;
	}

}
