package br.com.democracy.validation;

import java.lang.annotation.Annotation;

/**
 * The Class Validation. This class encapsulates the data necessary to validate
 * a field.
 */
public class Validation {

	/**
	 * The validation method. Represents the name of the method of
	 * ValidationHelper that will be invoked.
	 * */
	private String validationMethod;

	/**
	 * The message that will be throw in the ValidationException.
	 * */
	private String message;

	/** The params that will be used to invokate the method of ValidationHelper. */
	private Object[] params;

	/** The annotation. */
	private Annotation annotation;

	/** The expected value to be considered as valid. */
	private Boolean expected = true;

	/**
	 * Sets the validation method.
	 * 
	 * @param validationMethod
	 *            the new validation method
	 */
	public void setValidationMethod(String validationMethod) {
		this.validationMethod = validationMethod;
	}

	/**
	 * Gets the validation method.
	 * 
	 * @return the validation method
	 */
	public String getValidationMethod() {
		return validationMethod;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the annotation.
	 * 
	 * @param annotation
	 *            the new annotation
	 */
	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}

	/**
	 * Gets the annotation.
	 * 
	 * @return the annotation
	 */
	public Annotation getAnnotation() {
		return annotation;
	}

	/**
	 * Sets the params.
	 * 
	 * @param params
	 *            the new params
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}

	/**
	 * Gets the params.
	 * 
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}

	/**
	 * Sets the expected.
	 * 
	 * @param expected
	 *            the new expected
	 */
	public void setExpected(Boolean expected) {
		this.expected = expected;
	}

	/**
	 * Gets the expected.
	 * 
	 * @return the expected
	 */
	public Boolean getExpected() {
		return expected;
	}
}
