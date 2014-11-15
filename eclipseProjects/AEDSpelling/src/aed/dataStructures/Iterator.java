package aed.dataStructures;

import java.io.Serializable;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <E> Type of elements in iteration
 */
public interface Iterator<E> extends Serializable {
	
	/**
	 * Resets the iterator
	 */
	void reset();
	
	/**
	 * Verify if has next element in iteration
	 * 
	 * @return True if has next. Else false.
	 */
	boolean hasNext();
	
	/**
	 * Returns next element in iteration
	 * 
	 * @return Next element in iteration
	 * @throws NoSuchElementException If !hasNext();
	 */
	E next() throws NoSuchElementException;
}
