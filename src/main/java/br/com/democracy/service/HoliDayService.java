package br.com.democracy.service;

import java.util.List;

import br.com.democracy.dto.HoliDayDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.exception.ValidationException;

public interface HoliDayService {
	
	void newHoliday(HoliDayDTO holidayDTO) throws ValidationException, ServiceException;

	List<HoliDayDTO> listHoliDays();

}
