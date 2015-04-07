package br.com.democracy.helper;

public class EmailConstant {

	public static String POST_REGISTER_SUBJECT = "Democracy - Cadastro efetuado";
	
	public static String POST_REGISTER_BODY = "Olá {VAR_NAME}.<br>Seu cadastro foi efetuado com sucesso.<br>Aguarde a aprovação "
			+ "dos administradores.<br>Você receberá um novo email assim que algum administrador aprovar seu cadastro.";
	
	public static String POST_APPROVAL_SUBJECT = "Democracy - Cadastro aprovado!";
	
	public static String POST_APPROVAL_BODY = "Olá {VAR_NAME}.<br>Seu cadastro foi aprovado com sucesso.<br>"
			+ "A partir de agora você já pode logar normalmente e responder as perguntas do site.";
	
}
