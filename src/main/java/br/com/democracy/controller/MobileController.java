package br.com.democracy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.democracy.dto.ResponseDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ResultControllerHelper;
import br.com.democracy.helper.ValidationHelper;
import br.com.democracy.messages.Messages;
import br.com.democracy.service.MobileService;

@Resource
@Path("/mobile")
public class MobileController {

	@Autowired
	private Result result;

	@Autowired
	private MobileService mobileService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Post
	@Path("/authenticate")
	public void authenticate(String email, String password) {
		
		try {
			if(!ValidationHelper.isEmail(email)) {
				throw new ValidationException(Messages.EMAIL_INVALID);
			}
			
			if(!ValidationHelper.isPasswordUser(password)) {
				throw new ValidationException(Messages.PASSWORD_INVALID);
			}
			
			String token = mobileService.authenticate(email, password, request.getSession());
			
			ResultControllerHelper.returnResultWithoutRoot(result, new ResponseDTO(true, token));
		} catch (ServiceException e) {
			ResultControllerHelper.returnResultError(result, e.getMessage());
		}
		
	}
	
}
