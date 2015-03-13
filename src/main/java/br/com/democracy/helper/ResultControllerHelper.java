package br.com.democracy.helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.democracy.dto.ResponseDTO;
import br.com.democracy.messages.Messages;

public class ResultControllerHelper {

	public static void returnResult(final Result result, Object objectReturn) {
		result.use(Results.json()).from(objectReturn).recursive().serialize();
	}

	public static void returnResultWithoutRoot(final Result result,
			Object objectReturn) {
		result.use(Results.json()).withoutRoot().from(objectReturn).recursive()
				.serialize();
	}
	
	public static void returnResultError(final Result result, final String message) {
		ResponseDTO errorDTO = new ResponseDTO(false);
		errorDTO.setMessage(message);
		result.use(Results.http()).setStatusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
		result.use(Results.json()).withoutRoot().from(errorDTO).serialize();
	}
	
	public static void returnResultSuccess(final Result result) {
		ResponseDTO errorDTO = new ResponseDTO(true);
		errorDTO.setMessage(Messages.OK_MSG);
		
		result.use(Results.json()).withoutRoot().from(errorDTO).serialize();
	}
	
	public static void returnResultFailed(final Result result, final String message) {
		ResponseDTO errorDTO = new ResponseDTO(false);
		errorDTO.setMessage(Messages.OK_MSG);
		errorDTO.setMessage(message);
		result.use(Results.json()).withoutRoot().from(errorDTO).serialize();
	}
	
	public static void returnResultSuccessMessage(final Result result) {
		result.use(Results.json()).from(Messages.OK_MSG).serialize();
	}
	
	public static void returnResultHTTPError(final Result result) {
		result.use(Results.http()).setStatusCode(
				HttpURLConnection.HTTP_UNAVAILABLE);
	}

	public static void returnResultWithoutRootAndRegister(final Result result,
			Object objectReturn) {
		result.use(Results.json()).withoutRoot().from(objectReturn).exclude(
				"regDate", "regUser").recursive().serialize();
	}
	/**
	 * Builds the http image header.
	 */
	public static void buildHttpImageHeader(Result result) {
		result.use(Results.http()).addHeader("Cache-Control", "no-store");
		result.use(Results.http()).addHeader("Pragma", "no-cache");
		result.use(Results.http()).addDateHeader("Expires", 0);
		result.use(Results.http()).addHeader("Content-Type", "image/jpeg");
	}
	
	public static void returnHTMLJson(final Result result,
			final HttpServletResponse response, String message) {
		result.use(Results.http()).addHeader("Content-Type", "text/html");
		PrintWriter printer = null;
		try {
			printer = response.getWriter();
		} catch (IOException e1) {
			ResultControllerHelper.returnResultHTTPError(result);
		}
		printer.println(message);
	}
}
