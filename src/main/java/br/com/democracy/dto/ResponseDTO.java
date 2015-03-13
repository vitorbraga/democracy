package br.com.democracy.dto;

/**
 * The Class ResponseDTO. This DTO must be used as the default response DTO for
 * success messages and errors.
 */
public class ResponseDTO {

	/** The Constant TRUE. */
	public static final String TRUE = "true";

	/** The Constant FALSE. */
	public static final String FALSE = "false";

	/** The success. */
	private String success;

	/** The message. */
	private String message;

	/**
	 * Instantiates a new response dto.
	 */
	public ResponseDTO() {
		success = FALSE;
	}

	/**
	 * Instantiates a new response dto.
	 * 
	 * @param isSuccess
	 *            if this is a success response.
	 */
	public ResponseDTO(boolean isSuccess) {
		setSuccess(isSuccess);
	}
	
	public ResponseDTO(boolean isSuccess, String message){
		this(isSuccess);
		this.message = message;
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
	 * Sets the success.
	 * 
	 * @param isSuccess
	 *            the new success
	 */
	public void setSuccess(boolean isSuccess) {
		if (isSuccess) {
			success = TRUE;
		} else {
			success = FALSE;
		}
	}

	/**
	 * Gets the success.
	 * 
	 * @return the success
	 */
	public boolean getSuccess() {
		if (TRUE.equals(success)) {
			return true;
		} else {
			return false;
		}
	}
}
