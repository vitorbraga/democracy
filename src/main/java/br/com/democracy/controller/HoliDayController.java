package br.com.democracy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.dto.HoliDayDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.helper.ResultControllerHelper;
import br.com.democracy.service.HoliDayService;
import br.com.democracy.validation.Validator;

@Resource
public class HoliDayController {
	
	@Autowired
	private Result result;
	
	@Autowired
	private HoliDayService holiDayService;

	@Post
	@Path("/holiday")
	public void newHoliDay(HoliDayDTO holidayDTO) {

		try {
			// validar os dados
			Validator.validate(holidayDTO);
			
			// chamar o service para incluir no banco
			holiDayService.newHoliday(holidayDTO);
			
			// retorna sucesso
			ResultControllerHelper.returnResultSuccess(result);
		} catch (ValidationException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}

	}
	
	@Get
	@Path("/holidays")
	public void listHoliDays() {
		
		// chama o service para fazer a consulta
		List<HoliDayDTO> holiDays = holiDayService.listHoliDays(); 
		
		ResultControllerHelper.returnResult(result, holiDays);
		
	}
	
	
}
