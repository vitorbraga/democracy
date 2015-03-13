/*
 * Implements the Validation Exception for any validate error
 */
package br.com.democracy.exception;


/**
 * The Class ValidationException.
 * 
 * @author Alessandro
 */
public class ValidationException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new validation exception.
	 * 
	 * @param string
	 *            the string
	 * @param exception 
	 */
	public ValidationException(String string, Exception exception) {
		super(string);
	}
	
	public ValidationException(String string) {
		super(string);
	}
}
