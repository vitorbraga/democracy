package br.com.democracy.helper;

public class Mappers {
	
	private static final String questionStatusMap[] = { "", "Ativo", "Inativo" };

	private static final String genderMap[] = { "", "Masculino", "Feminino" };

	public static String questionStatus(Integer index) {
		return questionStatusMap[index];
	}
	
	public static String gender(Integer index) {
		return genderMap[index];
	}
}
