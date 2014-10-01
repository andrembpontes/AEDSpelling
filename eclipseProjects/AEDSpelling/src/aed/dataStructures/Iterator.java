package aed.dataStructures;

/**
 * Object to iterate by collection
 *
 * @author Andre Pontes (42845)
 * @param <T> Type of objects on iteration
 */
public interface Iterator<T> {
    /**
     * Return next element on current iteration
     *
     * @return Next element
     */
    T next();

    /**
     * Verify if has next element on current iteration
     *
     * @return True if has next element
     */
    boolean hasNext();
}
