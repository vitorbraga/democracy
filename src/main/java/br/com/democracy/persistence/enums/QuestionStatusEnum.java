package br.com.democracy.persistence.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum QuestionStatusEnum {

	ACTIVE(1),
	
	INACTIVE(2);

	private Integer id;

	private static final Map<Integer, QuestionStatusEnum> lookup = new HashMap<Integer, QuestionStatusEnum>();

	static {
		for (QuestionStatusEnum s : EnumSet.allOf(QuestionStatusEnum.class)) {
			lookup.put(s.id(), s);
		}
	}

	private QuestionStatusEnum(Integer id) {
		this.id = id;
	}

	public Integer id() {
		return this.id;
	}

	public static QuestionStatusEnum get(final Integer id) {
		return lookup.get(id);
	}

	public static QuestionStatusEnum[] getAllStatus() {
		return QuestionStatusEnum.values();
	}

}
