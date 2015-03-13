package br.com.democracy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.dto.TeamOutputDTO;
import br.com.democracy.helper.ResultControllerHelper;
import br.com.democracy.service.TeamService;

@Resource
public class TeamController {
	
	@Autowired
	private Result result;
	
	@Autowired
	private TeamService teamService;
	
	@Get
	@Path("/teams")
	public void getTeams() {
		List<TeamOutputDTO> resultLists = teamService.getTeams();

		if (resultLists.isEmpty()) {
			ResultControllerHelper.returnResultError(result,
					"Nenhum time encontrado");
		}

		ResultControllerHelper.returnResult(result, resultLists);
	}
}
