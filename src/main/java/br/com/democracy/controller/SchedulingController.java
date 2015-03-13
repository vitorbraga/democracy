package br.com.democracy.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.messages.MessagesLog;
import br.com.democracy.service.SchedulingService;

@Resource
@Path("/scheduling")
public class SchedulingController {

	static Logger logger = Logger.getLogger(SchedulingService.class);

	@Autowired
	private SchedulingService schedulingService;

	public SchedulingController() {

	}

	@Path("/deleteTemp")
	public void deleteTemp() {
		try {
			schedulingService.scriptDeleteTempFiles();
		} catch (ServiceException e) {
			logger.log(Level.ERROR, MessagesLog.DELETE_TEMPORARY_FILES);
		}
	}
	
	@Path("/testSchedule")
	public void testSchedule() {
		schedulingService.testSchedule();
	}

}
