package br.com.democracy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.dto.FootballPlayerInputDTO;
import br.com.democracy.dto.FootballPlayerOutputDTO;
import br.com.democracy.dto.SelectBoxDTO;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.helper.ResultControllerHelper;
import br.com.democracy.service.FootballPlayerService;
import br.com.democracy.validation.Validator;

@Resource
public class FootballPlayerController {
	
	@Autowired
	private Result result;
	
	@Autowired
	private FootballPlayerService footballPlayerService;
	
	@Get
	@Path("/footballplayers")
	public void getFootballPlayers(FootballPlayerInputDTO playerDTO) {

		try {
			
			if (playerDTO == null) {
				playerDTO = new FootballPlayerInputDTO();
			}
			
			// validar os parametros
			Validator.validate(playerDTO);

			// realizar consulta no service
			List<FootballPlayerOutputDTO> returnList = footballPlayerService
					.getFootballPlayers(playerDTO);

			// retornar dados
			ResultControllerHelper.returnResult(result, returnList);
		} catch (ValidationException exception) {
			ResultControllerHelper.returnResultError(result,
					exception.getMessage());
		}

	}
	
	@Get
	@Path("/getPlayersByName")
	public void getPlayersByName (String name) {
		List<FootballPlayerOutputDTO> returnList = footballPlayerService.getPlayersByName(name);
		ResultControllerHelper.returnResult(result, returnList);
	}
	
	@Get
	@Path("/getAllPlayers")
	public void getAllPlayers() {
		List<SelectBoxDTO> returnList = footballPlayerService.getAllPlayers();
		ResultControllerHelper.returnResult(result, returnList);
	}
	
	@Get
	@Path("/getAllPlayersSummary")
	public void getAllPlayersSummary() {
		List<SelectBoxDTO> returnList = footballPlayerService.getAllPlayersSummary();
		ResultControllerHelper.returnResult(result, returnList);
	}

}
