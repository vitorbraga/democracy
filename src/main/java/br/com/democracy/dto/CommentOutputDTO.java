package br.com.democracy.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.persistence.Comment;

public class CommentOutputDTO {

	private String id;

	private String comment;

	private String userName;
	
	private String date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static List<CommentOutputDTO> copyAll(List<Comment> comments)
			throws ServiceException {

		if (comments != null) {

			List<CommentOutputDTO> dtos = new ArrayList<CommentOutputDTO>();

			for (Comment comment : comments) {
				CommentOutputDTO dto = new CommentOutputDTO();
				dto.setId(ConvertHelper.convertIdToView(comment.getId()));
				dto.setComment(comment.getComment());
				dto.setUserName(comment.getUser().getName());
				dto.setDate(ConvertHelper.dateToViewSlash(comment.getRegDate()));
				
				dtos.add(dto);
			}

			return dtos;
		}

		return null;
	}
}
