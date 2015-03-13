package br.com.democracy.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ListDTO. This DTO can be used to transfer a generic list internally
 * or as a response. The field size, contains the full size of the list, even if
 * the list is paginated.
 * 
 * @param <T>
 *            the generic type
 */
public class ListDTO<T> {

	/** The size. */
	private Long size;

	/** The list. */
	private List<T> list;

	/**
	 * Instantiates a new list dto.
	 */
	public ListDTO() {
		this.setSize(0L);
		this.setList(new ArrayList<T>());
	}
	
	/**
	 * Instantiates a list dto with the given list.
	 */
	public ListDTO(List<T> list) {
		this.setSize(Long.valueOf(list.size()));
		this.setList(list);
	}

	/**
	 * Sets the size.
	 * 
	 * @param size
	 *            the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * Gets the size.
	 * 
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * Sets the list.
	 * 
	 * @param list
	 *            the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * Gets the list.
	 * 
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}
}
