package aed.dataStructures;

/**
 * Represents a list of elements
 *
 * @author Andre Pontes (42845)
 * @param <T> Type of list elements
 */
public interface List <T> {
    /**
     * Create and return a list iterator
     *
     * @return Iterator for list
     */
    Iterator<T> iterator();

    /**
     * Add an element to list
     *
     * @param elem Element to add
     * @return True if successfully added
     */
    boolean add(T elem);

    /**
     * Delete an element from list and return it
     *
     * @param elem Element to delete
     * @return Deleted element from list. Null if nothing deleted
     */
    T del(T elem);
    
    /**
     * Returns the list size 
     * @return List size 
     */
    int size();
}
