package br.com.democracy.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.helper.FileHelper;
import br.com.democracy.messages.MessagesLog;
import br.com.democracy.service.SchedulingService;

@Service
public class SchedulingServiceImpl implements SchedulingService {

	static Logger logger = Logger.getLogger(SchedulingService.class);

	public SchedulingServiceImpl() {
		// empty constructor
	}

	@Override
	public void scriptDeleteTempFiles() throws ServiceException {
		try {
			FileHelper.removeDirectoryFiles(FileHelper.getTempDir());
			FileHelper.removeDirectoryFiles(FileHelper.getDownloadsDir());
		} catch (ValidationException e) {
			throw new ServiceException(MessagesLog.DELETE_TEMPORARY_FILES);
		}
	}

	@Override
	public void testSchedule() {
		System.out.println("Metodo executou as:" + new Date());
	}

}
