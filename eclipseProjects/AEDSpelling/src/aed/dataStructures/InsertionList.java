package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <E> Type of List Elements
 */
public interface InsertionList<E> extends List<E> {

    /**
     * Inserts given element at list head
     *
     * @param element Element
     */
    void addFirst(E element);

    /**
     * Inserts given element at list tail
     *
     * @param element Element
     */
    void addLast(E element);

    /**
     * Returns first list element
     *
     * @return First element
     * @throws EmptyListException
     */
    E getFirst() throws EmptyListException;

    /**
     * Returns last list element
     *
     * @return Last element
     * @throws EmptyListException
     */
    E getLast() throws EmptyListException;

    /**
     * Return first element and remove it from list
     *
     * @return First element
     * @throws EmptyListException List is empty
     */
    E removeFirst() throws EmptyListException;

    /**
     * Return last element and remove it from list
     *
     * @return Last element
     * @throws EmptyListException List is empty
     */
    E removeLast() throws EmptyListException;

    /**
     * Insert given element at specified index
     *
     * @param index Index
     * @param element Element
     * @throws InvalidPositionException Invalid index
     */
    void insert(int index, E element) throws InvalidPositionException;

}
