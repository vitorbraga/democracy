package br.com.democracy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.dao.TeamDAO;
import br.com.democracy.dto.TeamOutputDTO;
import br.com.democracy.persistence.Team;
import br.com.democracy.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamDAO teamDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<TeamOutputDTO> getTeams() {
		// buscar todos os players no dao
		List<Team> teams = teamDAO.getAll();
		
		// copia os dados
		List<TeamOutputDTO> returnList = new ArrayList<TeamOutputDTO>();
		for (Team team : teams) {
			returnList.add(TeamOutputDTO.copy(team));
		}
		
		// retorna lista
		return returnList;
	}

}
