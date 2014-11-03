package aed.dataStructures;

/**
 * @author Andre Pontes (42845)
 */
public interface HashTable<E> extends Collection<E> {

    static final int DEFAULT_INITIAL_CAPACITY = 100;
    static final int GROWTH_TAX = 3;

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
    int hashTableOccupationPercentage();

    /**
     * Returns hash table size
     *
     * @return Hash table size
     */
    int hashTableSize();

    /**
     * Creates a new hash table with new capacity and insert all elements again
     * @param capacity New capacity
     */
    void rehash(int capacity);
}
