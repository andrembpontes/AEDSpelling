package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <E>Type of list elements
 */
public interface TwoWayIterator<E> extends Iterator<E> {
	
	/**
	 * Verify if exists previous element in current iteration
	 * 
	 * @return True if has previous. Else false.
	 */
	boolean hasPrevious();
	
	/**
	 * Go full forward and return last element
	 * 
	 * @return Last element in iteration
	 */
	E last();
	
	/**
	 * Returns previous element in iteration
	 * 
	 * @return Previous element in iteration
	 * @throws NoSuchElementException If !hasPrevious()
	 */
	E previous() throws NoSuchElementException;
	
}
