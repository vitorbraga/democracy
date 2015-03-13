package br.com.democracy.exception;

/**
 * The Class ServiceException.
 * 
 * @author Alessandro
 */
public class ServiceException extends Exception{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	Long erro;

	/**
	 * Instantiates a new service exception.
	 */
	public ServiceException() {
	}

	/**
	 * Instantiates a new service exception.
	 * 
	 * @param msg
	 *            the msg
	 */
	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new service exception.
	 * 
	 * @param msg
	 *            the msg
	 * @param t
	 *            the t
	 */
	public ServiceException(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * Instantiates a new service exception.
	 * 
	 * @param t
	 *            the throwable
	 */
	public ServiceException(Throwable t) {
		super(t);
	}

	public ServiceException(Long erro) {
		this.erro = erro;
	}

	public ServiceException(Long erro, Throwable t) {
		super(t);
		this.erro = erro;
	}

	public Long getErro() {
		return this.erro;
	}

}
