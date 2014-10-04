package aed.dataStructures;

public interface TwoWayIterator<E> extends Iterator<E>{
	/**
	 * Verify if exists previous element in current iteration
	 * 
	 * @return True if has previous. Else false.
	 */
	boolean hasPrevious( );

    /**
     * Returns previous element in iteration
     * 
     * @return Previous element in iteration
     * @throws NoSuchElementException If !hasPrevious()
     */
    E previous( ) throws NoSuchElementException;

    /**
     * Go full forward and return last element
     * 
     * @return Last element in iteration
     */
    E last( );

}
