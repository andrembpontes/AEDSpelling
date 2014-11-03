package aed.dataStructures;

/**
 * @author Andre Pontes (42845)
 */
public interface HashTable<E> extends Collection<E> {
    /**
     * Insert an element in hash table
     *
     * @param element Element to insert
     * @return Inserted element
     */
    E insert(E element);

    /**
     * Removes an element from hash table
     *
     * @param element Element to remove
     * @return Removed element, Null if element not found
     */
    E remove(E element);

    /**
     * Returns all elements in the hash table that indexes in same index
     *
     * @param element Element for indexation
     * @return Collection of elements that indexes in same index, Null if no elements
     */
    Collection<E> getHashIndex(Object element);

    /**
     * Calculates and returns hash table occupation factor
     *
     * @return Hash table occupation factor
     */
    int hashTableOccupationFactor();

    /**
     * Returns hash table size
     *
     * @return Hash table size
     */
    int hashTableSize();
}
