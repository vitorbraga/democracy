package br.com.democracy.service;

import br.com.democracy.exception.ServiceException;

public interface SchedulingService {

	void scriptDeleteTempFiles() throws ServiceException;

	void testSchedule();

}
