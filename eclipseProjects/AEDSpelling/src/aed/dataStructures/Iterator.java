package aed.dataStructures;

import java.io.Serializable;

public interface Iterator<E> extends Serializable {

    /**
     * Verify if has next element in iteration
     * 
     * @return True if has next. Else false.
     */
    boolean hasNext( );

    /**
     * Returns next element in iteration
     * 
     * @return Next element in iteration
     * @throws NoSuchElementException If !hasNext();
     */
    E next() throws NoSuchElementException;

    /**
     * Restarts iteration and return first element
     * 
     * @return First element
     */
    E first();
}
