package br.com.democracy.helper;

import java.util.Formatter;
import java.util.Locale;

/**
 * Class shamelessly copied from Evolucard project. Must change the css styles
 * of it before start sending emails.
 */
public class EmailMsgBuilder {

	/*
	 * EMAIL SIGNATURE Logos: Evc + Facebook + Twitter + Linkedin
	 * 
	 * There is two types because of the href position on every template is
	 * different
	 */
	private static final String emailSignature7 = 
			"<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">Atenciosamente,<br/><b>Equipe EvoluCard</b><br/> Pague com mais segurança.</p>"
			+ "<div>"
			+ "<a href=\"https://www.evolucard.com.br/\" target=\"_blank\"><img src=\"http://%7$s/resources/images/logoev28px.png\"></a>"
			+ "<span>&nbsp;</span>"
			+ "<a href=\"http://www.facebook.com/EvoluCard\" target=\"_blank\"><img src=\"http://%7$s/resources/images/face28px.png\"></a>"
			+ "<span>&nbsp;</span>"
			+ "<a href=\"http://twitter.com/#!/evolucardsa\" target=\"_blank\"><img src=\"http://%7$s/resources/images/twitter28px.png\"></a>"
			+ "<span>&nbsp;</span>"
			+ "<a href=\"http://www.linkedin.com/company/evolucard\" target=\"_blank\"><img src=\"http://%7$s/resources/images/linkedin28px.png\"></a>"
			+ "</div>"
			+ "<p style=\"text-align: center; font-family:'Times New Roman';font-size:13px; color:#858585;\"><i>Este é um e-mail automático. Por favor, não o responda.</i></p>";

	private static final String emailSignature6 = 
			"<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">Atenciosamente,<br/><b>Equipe EvoluCard</b><br/> Pague com mais segurança.</p>"
			+ "<div>"
			+ "<a href=\"https://www.evolucard.com.br/\" target=\"_blank\"><img src=\"http://%6$s/resources/images/logoev28px.png\"></a>"
			+ "<span>&nbsp;</span>"
			+ "<a href=\"http://www.facebook.com/EvoluCard\" target=\"_blank\"><img src=\"http://%6$s/resources/images/face28px.png\"></a>"
			+ "<span>&nbsp;</span>"
			+ "<a href=\"http://twitter.com/#!/evolucardsa\" target=\"_blank\"><img src=\"http://%6$s/resources/images/twitter28px.png\"></a>"
			+ "<span>&nbsp;</span>"
			+ "<a href=\"http://www.linkedin.com/company/evolucard\" target=\"_blank\"><img src=\"http://%6$s/resources/images/linkedin28px.png\"></a>"
			+ "</div>"
			+  "<p style=\"text-align: center; font-family:'Times New Roman';font-size:13px; color:#858585;\"><i>Este é um e-mail automático. Por favor, não o responda.</i></p>";

	private static final String defaultSalute = "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">Qualquer dúvida envie um e-mail para <a href=\"mailto:contato@evolucard.com.br\">contato@evolucard.com.br</p>";
	
	private static final String otherSalute = "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">Qualquer dúvida estamos à disposição.</p>";
	

	/*
	 * TEMPLATE Assunto: EvoluCard: Assunto Mensagem:
	 * 
	 * Assunto (%1)
	 * 
	 * Olá, Fulano(%2).
	 * 
	 * Mensagem. (%3)
	 * 
	 * Por favor, clique aqui(href=%4) para confirmar seu cadastro (%5 = para
	 * ...).
	 * 
	 * Qualquer dúvida envie um e-mail para contato@evolucard.com.br.
	 * 
	 * Atenciosamente, Equipe EvoluCard.
	 */

	private static final String template = ""
			+ "<div style=\"width:640px;background-color:#f5f5f5;\">"
			+ "<div style=\"border-top:20px solid #41a756;\">"
			+ "<div style=\"margin:25px; border-bottom:1px solid #c4c9c4;\">"
			+ "<img src=\"http://%6$s/resources/images/mail_logo.png\"/>"
			+ "</div>"
			+ "</div>"
			+ "<div style=\"margin:40px 25px 25px 25px; display:inline-block;\">"
			+ "<div style=\"width:190px; float:left;\">"
			+ "<img src=\"http://%6$s/resources/images/mail_image.png\"/>"
			+ "</div>"
			+ "<div style=\"margin-left:200px;\">"
			+ "<p style=\"font-family:'Times New Roman';font-size:20px; color:#41a756; margin:0px;\">%1$s</p>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">Olá, %2$s</p>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">%3$s</p>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">Por favor, <a style=\"color:#41a756; text-decoration:none;\" href=\"%4$s\" target=\"_blank\">clique aqui </a>%5$s</p>"
			+ defaultSalute
			+ emailSignature6
			+ "</div>" + "</div>" + "</div>";

	/*
	 * TEMPLATE BUTTON Assunto: EvoluCard: Assunto Mensagem:
	 * 
	 * Assunto (%1)
	 * 
	 * Olá, Fulano(%2).
	 * 
	 * Mensagem. (%3)
	 * 
	 * Link Botao(href=%4)
	 * 
	 * Texto do botao (%5).
	 * 
	 * Qualquer dúvida envie um e-mail para contato@evolucard.com.br.
	 * 
	 * Atenciosamente, Equipe EvoluCard.
	 */

	private static final String templateButton = ""
			+ "<div style=\"width:640px;background-color:#f5f5f5;\">"
			+ "<div style=\"border-top:20px solid #41a756;\">"
			+ "<div style=\"margin:25px; border-bottom:1px solid #c4c9c4;\">"
			+ "<img src=\"http://%6$s/resources/images/mail_logo.png\"/>"
			+ "</div>"
			+ "</div>"
			+ "<div style=\"margin:40px 25px 25px 25px; display:inline-block;\">"
			+ "<div style=\"width:190px; float:left;\">"
			+ "<img src=\"http://%6$s/resources/images/mail_image.png\"/>"
			+ "</div>"
			+ "<div style=\"margin-left:200px;\">"
			+ "<p style=\"font-family:'Times New Roman';font-size:20px; color:#41a756; margin:0px;\">%1$s</p>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">Olá, %2$s</p>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">%3$s</p>"
			+ "<a style=\"padding: 5px 0px;\" href=\"%4$s\" target=\"_blank\">"
			+ "%4$s"
			+ "</a>"
			+ defaultSalute
			+ emailSignature6
			+ "</div>" + "</div>" + "</div>";

	/*
	 * TEMPLATE BUTTON TEXT Assunto: EvoluCard: Assunto Mensagem:
	 * 
	 * Assunto (%1)
	 * 
	 * Olá, Fulano(%2).
	 * 
	 * Mensagem. (%3)
	 * 
	 * Link Botão(href=%4)
	 * 
	 * Texto do botao (%5).
	 * 
	 * Mensagem depois do botao (%6)
	 * 
	 * Qualquer dúvida envie um e-mail para contato@evolucard.com.br.
	 * 
	 * Atenciosamente, Equipe EvoluCard.
	 */

	private static final String templateButtonText = ""
			+ "<div style=\"width:640px;background-color:#f5f5f5;\">"
			+ "<div style=\"border-top:20px solid #41a756;\">"
			+ "<div style=\"margin:25px; border-bottom:1px solid #c4c9c4;\">"
			+ "<img src=\"http://%7$s/resources/images/mail_logo.png\"/>"
			+ "</div>"
			+ "</div>"
			+ "<div style=\"margin:40px 25px 25px 25px; display:inline-block;\">"
			+ "<div style=\"width:190px; float:left;\">"
			+ "<img src=\"http://%7$s/resources/images/mail_image.png\"/>"
			+ "</div>"
			+ "<div style=\"margin-left:200px;\">"
			+ "<p style=\"font-family:'Times New Roman';font-size:20px; color:#41a756; margin:0px;\">%1$s</p>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">Olá, %2$s</p>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">%3$s</p>"
			+ "<a style=\"padding: 5px 0px;\" href=\"%4$s\" target=\"_blank\">"
			+ "%4$s"
			+ "</a>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">%6$s</p>"
			+ defaultSalute
			+ emailSignature7
			+ "</div>" + "</div>" + "</div>";

	/*
	 * TEMPLATE BUTTON TEXT WITHOUT EMAIL Assunto: EvoluCard: Assunto Mensagem:
	 * 
	 * Assunto (%1)
	 * 
	 * Olá, Fulano(%2).
	 * 
	 * Mensagem. (%3)
	 * 
	 * Link Botão(href=%4)
	 * 
	 * Texto do botao (%5).
	 * 
	 * Mensagem depois do botao (%6)
	 * 
	 * Atenciosamente, Equipe EvoluCard.
	 */

	private static final String templateButtonText2 = ""
			+ "<div style=\"width:640px;background-color:#f5f5f5;\">"
			+ "<div style=\"border-top:20px solid #41a756;\">"
			+ "<div style=\"margin:25px; border-bottom:1px solid #c4c9c4;\">"
			+ "<img src=\"http://%7$s/resources/images/mail_logo.png\"/>"
			+ "</div>"
			+ "</div>"
			+ "<div style=\"margin:40px 25px 25px 25px; display:inline-block;\">"
			+ "<div style=\"width:190px; float:left;\">"
			+ "<img src=\"http://%7$s/resources/images/mail_image.png\"/>"
			+ "</div>"
			+ "<div style=\"margin-left:200px;\">"
			+ "<p style=\"font-family:'Times New Roman';font-size:20px; color:#41a756; margin:0px;\">%1$s</p>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">Olá, %2$s</p>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">%3$s</p>"
			+ "<a style=\"padding: 5px 0px;\" href=\"%4$s\" target=\"_blank\">"
			+ "%4$s"
			+ "</a>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">%6$s</p>"
			+ emailSignature7
			+ "</div>" + "</div>" + "</div>";

	/*
	 * INTERN TEMPLATE Assunto: EvoluCard: Assunto Mensagem:
	 * 
	 * Assunto (%1)
	 * 
	 * Olá,.
	 * 
	 * Mensagem. (%3)
	 */

	private static final String internTemplate = ""
			+ "<div style=\"width:640px;background-color:#f5f5f5;\">"
			+ "<div style=\"border-top:20px solid #41a756;\">"
			+ "<div style=\"margin:25px; border-bottom:1px solid #c4c9c4;\">"
			+ "<img src=\"http://10.10.10.14:8080/resources/images/mail_logo.png\"/>"
			+ "</div>"
			+ "</div>"
			+ "<div style=\"margin:40px 25px 25px 25px; display:inline-block;\">"
			+ "<div style=\"width:190px; float:left;\">"
			+ "<img src=\"http://10.10.10.14:8080/resources/images/mail_image.png\"/>"
			+ "</div>"
			+ "<div style=\"margin-left:200px;\">"
			+ "<p style=\"font-family:'Times New Roman';font-size:20px; color:#41a756; margin:0px;\">%1$s</p>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">Olá,</p>"
			+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">%3$s</p>"
			+ "</div>" + "</div>" + "</div>";

	// Uses template if passed all arguments, genericTemplate if missing href or
	// linkExplanation
	public static String buildMsg(String subject, String name, String content,
			String href, String linkExplanation, String serverName,
			boolean defaultSalute) {

		if (name != null && !name.isEmpty()) {
			name = name.concat(".");
		}

		Object[] argumentos = new Object[] { subject, name, content, href,
				linkExplanation, serverName };

		Formatter formatter = getFormatter();

		if (serverName == null && href == null && linkExplanation == null) {
			return formatter.format(internTemplate, argumentos).toString();
		} else if (href == null || linkExplanation == null) {
			return formatter.format(EmailMsgBuilder.getGenericTemplate(defaultSalute),
					argumentos).toString();
		} else {
			return formatter.format(template, argumentos).toString();
		}
	}

	// Uses templateButton or templateButtonText depending on parameter
	// contentFinal
	public static String buildMsg(String subject, String name, String content,
			String href, String buttonText, String contentFinal,
			String serverName) {

		if (name != null && !name.isEmpty()) {
			name = name.concat(".");
		}

		Object[] argumentos = null;

		Formatter formatter = getFormatter();

		if (contentFinal != null && !contentFinal.equals("")) {
			argumentos = new Object[] { subject, name, content, href,
					buttonText, contentFinal, serverName };
			return formatter.format(templateButtonText, argumentos).toString();
		}

		argumentos = new Object[] { subject, name, content, href, buttonText,
				serverName };

		return formatter.format(templateButton, argumentos).toString();
	}

	public static String buildMsg(String subject, String name, String content,
			String href, String buttonText, String contentFinal, String flag,
			String serverName) {

		if (name != null && !name.isEmpty()) {
			name = name.concat(".");
		}

		Object[] argumentos = null;

		Formatter formatter = getFormatter();

		if (contentFinal != null && !contentFinal.equals("")) {
			argumentos = new Object[] { subject, name, content, href,
					buttonText, contentFinal, serverName };
			return formatter.format(templateButtonText2, argumentos).toString();
		}

		argumentos = new Object[] { subject, name, content, href, buttonText,
				serverName };

		return formatter.format(templateButton, argumentos).toString();
	}

	// Uses genericTemplate
	public static String buildMsg(String subject, String name, String content,
			String serverName, boolean defaultSalute) {
		return buildMsg(subject, name, content, null, null, serverName,
				defaultSalute);
	}

	private static Formatter getFormatter() {
		Formatter formatter = new Formatter(new Locale("pt", "BR"));
		return formatter;
	}
	
	
	/*
	 * GENERIC TEMPLATE Assunto: EvoluCard: Assunto Mensagem:
	 * 
	 * Assunto (%1)
	 * 
	 * Olá, Fulano(%2).
	 * 
	 * Mensagem. (%3)
	 * 
	 * A despedida depende da variável defaultSalute
	 * 
	 * Atenciosamente, Equipe EvoluCard.
	 */
	public static String getGenericTemplate(boolean defaultSalute ) {
		return ""
				+ "<div style=\"width:640px;background-color:#f5f5f5;\">"
				+ "<div style=\"border-top:20px solid #41a756;\">"
				+ "<div style=\"margin:25px; border-bottom:1px solid #c4c9c4;\">"
				+ "<img src=\"http://%6$s/resources/images/mail_logo.png\"/>"
				+ "</div>"
				+ "</div>"
				+ "<div style=\"margin:40px 25px 25px 25px; display:inline-block;\">"
				+ "<div style=\"width:190px; float:left;\">"
				+ "<img src=\"http://%6$s/resources/images/mail_image.png\"/>"
				+ "</div>"
				+ "<div style=\"margin-left:200px;\">"
				+ "<p style=\"font-family:'Times New Roman';font-size:20px; color:#41a756; margin:0px;\">%1$s</p>"
				+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">Olá, %2$s</p>"
				+ "<p style=\"font-family:'Times New Roman';font-size:14px; color:#858585;\">%3$s</p>"
				+ (defaultSalute ? EmailMsgBuilder.defaultSalute : otherSalute)
				+ emailSignature6
				+ "</div>" + "</div>" + "</div>";
		
	}
}
