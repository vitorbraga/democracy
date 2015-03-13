package br.com.democracy.helper;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.mail.internet.InternetAddress;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.persistence.enums.MessagesWebServiceEnum;

/**
 * The Class EmailHelper.
 */
public class EmailHelper implements Runnable {

	/** The queue of Email */
	private ArrayBlockingQueue<HtmlEmail> queue;

	/** The thread of the Email Sender */
	private Thread emailThread;

	/** The instance. */
	private static EmailHelper instance = new EmailHelper();

	/** The CHARSET. */
	private static String CHARSET = "UTF-8";

	/** The logger. */
	static Logger logger = Logger.getLogger(EmailHelper.class);

	/** The host name. */
	private String hostName;

	/** The server port. */
	private Integer serverPort;

	/** The tls. */
	private Boolean tls;

	/** The ssl. */
	private Boolean ssl;

	/** The username. */
	private String username;

	/** The password. */
	private String password;

	/** The from name. */
	private String fromName;

	/** The server name. */
	private String serverName;

	/** The subject reset password. */
	private String subjectResetPassword;

	/** The thread sleep time */
	private long sleepTime;

	/**
	 * Instantiates a new email helper.
	 */
	public EmailHelper() {
		XMLConfiguration configuration = new XMLConfiguration();

		try {
			configuration.load("email.xml");
		} catch (ConfigurationException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// initializa queue
		queue = new ArrayBlockingQueue<HtmlEmail>(100);

		// load configs
		hostName = configuration.getString("host.name");
		serverPort = configuration.getInteger("host.port", 25);
		ssl = configuration.getBoolean("host.ssl");
		tls = configuration.getBoolean("host.tls");
		password = configuration.getString("password");
		username = configuration.getString("username");
		fromName = configuration.getString("name");
		serverName = configuration.getString("serverName");
		sleepTime = configuration.getLong("sleepTime");

		setSubjectResetPassword(configuration
				.getString("subject.resetPassword"));

		// configures and starts the thread
		emailThread = new Thread(this);
		emailThread.setName("EmailSenderThread");
		emailThread.start();

	}

	/**
	 * Gets the single instance of EmailHelper.
	 * 
	 * @return single instance of EmailHelper
	 */
	public static EmailHelper getInstance() {
		return instance;
	}

	public static void setInstance(EmailHelper newInstance) {
		instance = newInstance;
		return;
	}

	private synchronized ArrayBlockingQueue<HtmlEmail> getQueue() {
		return queue;
	}

	/**
	 * Configure email.
	 * 
	 * @return the html email
	 */
	private HtmlEmail configureEmail(String fromName) {
		HtmlEmail email = new HtmlEmail();

		try {
			email.setHostName(hostName);
			email.setSmtpPort(serverPort);
			email.setSSL(ssl);
			email.setTLS(tls);
			email.setFrom(username,
					fromName == null ? this.fromName : fromName, CHARSET);
			email.setAuthentication(username, password);
			email.setCharset(CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return email;
	}

	/**
	 * Send email.
	 * 
	 * @param toEmail
	 *            the to email
	 * @param toName
	 *            the to name
	 * @param fromName
	 *            the from name - can be null
	 * @param replyTo
	 *            the reply to - can be null
	 * @param subject
	 *            the subject
	 * @param content
	 *            the content
	 * @return true, if successful
	 */
	public boolean sendEmail(String toEmail, String toName, String fromName,
			String replyTo, String subject, String content)
			throws ServiceException {

		try {
			HtmlEmail email = configureEmail(fromName);

			List<InternetAddress> toList = new ArrayList<InternetAddress>();

			InternetAddress to = new InternetAddress(toEmail, toName);
			toList.add(to);
			email.setTo(toList);

			if (replyTo != null && !replyTo.isEmpty()) {
				List<InternetAddress> replyToList = new ArrayList<InternetAddress>();
				InternetAddress replyToAdress = new InternetAddress(replyTo);
				replyToList.add(replyToAdress);
				email.setReplyTo(replyToList);
			}

			email.setSubject(subject);
			email.setHtmlMsg(content);

			getQueue().add(email);
		} catch (Exception e) {
			throw new ServiceException(
					MessagesWebServiceEnum.UNKNOW_ERRO_ID.toString());
		}
		return true;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws EmailException
	 *             the email exception
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws ServiceException
	 */
	public static void main(String args[]) throws EmailException,
			MalformedURLException, ServiceException {
		EmailHelper emailHelper = EmailHelper.getInstance();

		emailHelper.sendEmail("joaonlima@gmail.com", "Joao Luiz Lima", null,
				"joaonlima@gmail.com", "Assunto de teste", EmailMsgBuilder
						.buildMsg("Assunto de teste", "João Luiz",
								"Teste a EvoluCard.", "localhost:8080", true));

	}

	@Override
	public void run() {
		HtmlEmail email = null;

		synchronized (this) {
			while (true) {
				try {
					while (!getQueue().isEmpty()) {
						email = getQueue().poll();
						if (email != null) {
							email.send();
						}
					}
					this.wait(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (EmailException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setSubjectResetPassword(String subjectResetPassword) {
		this.subjectResetPassword = subjectResetPassword;
	}

	public String getSubjectResetPassword() {
		return subjectResetPassword;
	}
}
