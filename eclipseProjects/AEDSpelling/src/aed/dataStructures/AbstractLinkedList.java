package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <E> Type of list elements
 */
public abstract class AbstractLinkedList<E> extends AbstractList<E> implements List<E>{
	
	static final long	serialVersionUID	= 0L;
	
	/**
	 * List head
	 */
	protected Node<E>		first;
	
	/**
	 * List tail
	 */
	protected Node<E>		last;

	public AbstractLinkedList() {
        this.clear();
	}


	@Override
	public int find(E element) {
		int i = 0;
		
		Node node = this.first;
		
		while (node != null && !node.getValue().equals(element)) {
			node = node.getNext();
			i++;
		}
		if (node == null)
			return -1;
		else
			return i;
	}
	
	/**
	 * Look for given element at list and return correspondent node.
	 * 
	 * @param element Element to found
	 * @return Node of given node. Null if not found
	 */

	protected Node<E> findNode(E element) {
		Node<E> node = this.first;
		do {
			if (node.getValue().equals(element))
				return node;
			node = node.getNext();
		}
		while (node.getNext() != null);
		return null;
	}
	
	@Override
	public E get(int index) throws InvalidPositionException {
		super.validateIndex(index);
		return this.getNode(index).getValue();
	}

	/**
	 * Returns first list node
	 * 
	 * @return First node
	 */
	protected Node<E> getFirstNode() {
        return this.first;
	}

	/**
	 * Returns last list node
	 *
	 * @return Last node
	 */
	protected Node<E> getLastNode() {
		return this.last;
	}
	
	/**
	 * Return node at given index
	 * 
	 * @param index Node index to get
	 * @return Node at given index
	 */
	protected Node<E> getNode(int index) {
        Node<E> node;
		if (index <= (super.size - 1) / 2) {
			node = this.first;
			for (int i = 0; i < index; i++)
				node = node.getNext();
		}
		else {
			node = this.last;
			for (int i = super.size - 1; i > index; i--)
				node = node.getPrevious();
		}
		return node;
	}

    @Override
    public void clear() {
        this.last = null;
        this.first = null;
        this.size = 0;
    }

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator<E>(this);
	}
	
	@Override
	public boolean remove(E element) {
		Node<E> node = this.findNode(element);
		
		if (node == null)
			return false;
		else {
			if (node == this.first)
				this.removeFirstNode();
			else if (node == this.last)
				this.removeLastNode();
			else
				this.removeNode(node);
			return true;
		}
	}
	
	@Override
	public E remove(int index) throws InvalidPositionException {
		super.validateIndex(index);

        Node<E> toRemove = this.getNode(index);
        this.removeNode(toRemove);
        return toRemove.getValue();

	}
	
	/**
	 * Remove first node
	 */
	protected void removeFirstNode() {
		this.first = this.first.getNext();
		if (this.first == null)
			this.last = null;
		else
			this.first.setPrevious(null);
		super.size--;
	}
	
	/**
	 * Remove last node
	 */
	protected void removeLastNode() {
		this.last = this.last.getPrevious();
		if (this.last == null)
			this.first = null;
		else
			this.last.setNext(null);
		super.size--;
	}
	
	/**
	 * Remove given node
	 * 
	 * @param node Node to remove
	 */
	protected void removeNode(Node<E> node) {
		Node<E> prev = node.getPrevious();
		Node<E> next = node.getNext();
		prev.setNext(next);
		next.setPrevious(prev);
		super.size--;
	}

    /**
     * Appends one list to another
     * @param list list to append
     */
    public void append(LinkedList<E> list) {
        this.last.setNext(list.first);
        list.last.setPrevious(this.first);
        this.size += list.size();
        list.clear();
    }
}
