package br.com.democracy.dto;

public class SelectBoxDTO {
	
	private String id;
	
	private String desc;
	
	public static SelectBoxDTO copyPlayer(Object[] object) {
		SelectBoxDTO selectBoxDTO = new SelectBoxDTO();
		selectBoxDTO.setId(object[0].toString());
		selectBoxDTO.setDesc(object[1].toString() + " / "
				+ object[2].toString());
		
		return selectBoxDTO;
	}

	public String getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static SelectBoxDTO copyPlayerSummary(Object[] object) {
		SelectBoxDTO selectBoxDTO = new SelectBoxDTO();
		selectBoxDTO.setId(object[0].toString());
		selectBoxDTO
				.setDesc(object[1].toString() + " (" + object[2].toString()
						+ " " + object[3].toString() + " "
						+ object[4].toString() + ")");
		
		return selectBoxDTO;
	}

}
