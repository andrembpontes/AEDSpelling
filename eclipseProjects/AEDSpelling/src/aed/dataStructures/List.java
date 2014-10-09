package aed.dataStructures;

import java.io.Serializable;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 *
 * @param <E>Type of list elements
 */
public interface List<E> extends Serializable {

	/**
	 * Inserts given element at list tail Same as addLast()
	 * 
	 * @param element
	 *            Element
	 */
	void add(E element);

	/**
	 * Inserts given element at list head
	 * 
	 * @param element
	 *            Element
	 */
	void addFirst(E element);

	// Returns an iterator of the elements in the list (in proper sequence).

	/**
	 * Inserts given element at list tail
	 * 
	 * @param element
	 *            Element
	 */
	void addLast(E element);

	/**
	 * Look for given element and return its index
	 * 
	 * @param element
	 *            Element
	 * @return Element index. -1 if no such element
	 */
	int find(E element);

	/**
	 * Return element at specified index
	 * 
	 * @param index
	 *            Index of element
	 * @return Element at given index
	 * @throws InvalidPositionException
	 */
	E get(int index) throws InvalidPositionException;

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
	 * Insert given element at specified index
	 * 
	 * @param index
	 *            Index
	 * @param element
	 *            Element
	 * @throws InvalidPositionException
	 *             Invalid index
	 */
	void insert(int index, E element) throws InvalidPositionException;

	/**
	 * Verify is list is empty
	 * 
	 * @return True if list is empty
	 */
	boolean isEmpty();

	/**
	 * Creates and returns a new iterator
	 * 
	 * @return Iterator
	 */
	Iterator<E> iterator();

	/**
	 * Remove given element
	 * 
	 * @param element
	 *            Element to remove
	 * @return True if elemet was removed. Else false
	 */
	boolean remove(E element);

	/**
	 * Remove element at specified position and returns it
	 * 
	 * @param index
	 *            Index
	 * @return Removed element
	 * @throws InvalidPositionException
	 *             Invalid index
	 */
	E remove(int index) throws InvalidPositionException;

	/**
	 * Return first element and remove it from list
	 * 
	 * @return First element
	 * @throws EmptyListException
	 *             List is empty
	 */
	E removeFirst() throws EmptyListException;

	/**
	 * Return last element and remove it from list
	 * 
	 * @return Last element
	 * @throws EmptyListException
	 *             List is empty
	 */
	E removeLast() throws EmptyListException;

	// Removes the first occurrence of the specified element from the list
	// and returns true, if the list contains the element.
	// Otherwise, returns false.

	/**
	 * Returns list size
	 * 
	 * @return Size
	 */
	int size();

}
