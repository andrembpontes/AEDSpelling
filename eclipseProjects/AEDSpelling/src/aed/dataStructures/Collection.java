package aed.dataStructures;

import java.io.Serializable;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <E> Type of collection elements
 */
public interface Collection <E> extends Serializable {
    /**
     * Returns number of collection elements
     *
     * @return Number of collection elements
     */
    int size();

    /**
     * Verify if Collection is full
     *
     * @return True if Collection is full, else False
     */
    boolean isFull();

    /**
     * Verify if Collection is empty
     *
     * @return True if Collection is empty, else False
     */
    boolean isEmpty();

    /**
     * Verifies if Collection contains given element
     *
     * @param element Element to find
     * @return True if contains element, else False
     */
    boolean contains(E element);

    /**
     * Creates and returns a new iterator
     *
     * @return Iterator
     */
    Iterator<E> iterator();

    /**
     * Removes all elements of the collection
     */
    void clear();
}
