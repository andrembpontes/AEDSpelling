package aed.dataStructures;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <E> Type of elements in iteration
 */
class LinkedListIterator<E> implements TwoWayIterator<E> {

	static final long serialVersionUID = 0L;

	/**
	 * LinkedList of current iteration
	 */
	protected AbstractLinkedList<E> list;

	/**
	 * Next node in current iteration
	 */
	protected Node<E> next;

	/**
	 * Previous node in current iteration
	 */
	protected Node<E> previous;

    protected LinkedListIterator(AbstractLinkedList<E> list) {
		this.list = list;
        this.reset();
	}

	@Override
	public void reset() {
        this.next = list.getFirstNode();
        this.previous = null;
	}

	@Override
	public boolean hasNext() {
		return this.next != null;
	}

	@Override
	public boolean hasPrevious() {
		return this.previous != null;
	}

	@Override
	public E last() {
		return this.returnNode(this.list.getLastNode());
	}

	@Override
	public E next() throws NoSuchElementException {
		if (!this.hasNext())
			throw new NoSuchElementException();

		return this.returnNode(this.next);
	}

	@Override
	public E previous() throws NoSuchElementException {
		if (!this.hasPrevious())
			throw new NoSuchElementException();

		return this.returnNode(this.previous);
	}

	/**
	 * Return node value and set previous and next pointers with correct data
	 * 
	 * @param node
	 *            Node with value to return
	 * @return Node value
	 */
	private E returnNode(Node<E> node) {
		E toReturn = node.getValue();
		this.previous = node.getPrevious();
		this.next = node.getNext();

		return toReturn;
	}

}
