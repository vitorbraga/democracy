package br.com.democracy.interfaces;

/**
 * The Interface SelectBox. 
 * This interface must be used in Persistences wich have SelectBox.
 */
public interface GroupSelectBox extends SelectBox{
	
	/**
	 * Gets the group.
	 *
	 * @return the group
	 */
	public String getGroupBy();

}
