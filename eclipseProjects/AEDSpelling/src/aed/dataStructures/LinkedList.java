package aed.dataStructures;

import java.io.Serializable;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <E> Type of list elements
 */
public class LinkedList<E> extends AbstractList<E> implements List<E>{
	
	class Node implements Serializable {

		static final long	serialVersionUID	= 0L;

		/**
		 * Stores value
		 */
		private E			value;
		
		/**
		 * Pointer to previous node
		 */
		private Node		previous;
		
		/**
		 * Pointer to next node
		 */
		private Node		next;
		
		/**
		 * Creates a node without previous or next node
		 * 
		 * @param value Node value
		 */
		public Node(E value) {
			this(value, null, null);
		}
		
		/**
		 * Creates a node with only previous pointer
		 * 
		 * @param value Node value
		 * @param previous pointer to previous node
		 */
		public Node(E value, Node previous) {
			this(value, previous, null);
		}
		
		/**
		 * Creates a node with given previous and next node pointers
		 * 
		 * @param value Node value
		 * @param previous Pointer to previous node
		 * @param next Pointer to next node
		 */
		public Node(E value, Node previous, Node next) {
			this.value = value;
			this.previous = previous;
			this.next = next;
		}
		
		/**
		 * Return next node
		 * 
		 * @return Next node
		 */
		public Node getNext() {
			return this.next;
		}
		
		/**
		 * Return previous node
		 * 
		 * @return Previous node
		 */
		public Node getPrevious() {
			return this.previous;
		}
		
		/**
		 * Return node value
		 * 
		 * @return Value
		 */
		public E getValue() {
			return this.value;
		}
		
		/**
		 * Set next node and return old next node
		 * 
		 * @param next Pointer to next node
		 * @return Old next node
		 */
		public Node setNext(Node next) {
			Node oldNext = this.previous;
			this.next = next;
			return oldNext;
		}
		
		/**
		 * Set previous node and return old previous node
		 * 
		 * @param previous Pointer to previous node
		 * @return Old previous node
		 */
		public Node setPrevious(Node previous) {
			Node oldPrevious = this.previous;
			this.previous = previous;
			return oldPrevious;
		}
		
		/**
		 * Set node value
		 * 
		 * @param value Node value
		 */
		public void setValue(E value) {
			this.value = value;
		}
		
	}
	
	static final long	serialVersionUID	= 0L;
	
	/**
	 * List head
	 */
	private Node		first;
	
	/**
	 * List tail
	 */
	private Node		last;

	public LinkedList() {
		this.first = null;
		this.last = null;
	}
	
	@Override
	public void add(E element) {
		this.addLast(element);
	}
	
	@Override
	public void addFirst(E element) {
		Node toAdd = new Node(element, null, this.first);
		
		if (this.isEmpty())
			this.last = toAdd;
		else
			this.first.setPrevious(toAdd);
		
		this.first = toAdd;
		super.size++;
	}
	
	@Override
	public void addLast(E element) {
		Node toAdd = new Node(element, this.last, null);
		
		if (this.isEmpty())
			this.first = toAdd;
		else
			this.last.setNext(toAdd);
		
		this.last = toAdd;
		super.size++;
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
	protected Node findNode(E element) {
		Node node = this.first;
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
	
	@Override
	public E getFirst() throws EmptyListException {
		super.validateNotEmpty();
		return this.first.getValue();
	}
	
	/**
	 * Returns first list node
	 * 
	 * @return First node
	 */
	protected Node getFirstNode() {
        return this.first;
	}
	
	@Override
	public E getLast() throws EmptyListException {
		validateNotEmpty();
		return this.last.getValue();
	}
	
	/**
	 * Returns last list node
	 * 
	 * @return Last node
	 */
	protected Node getLastNode() {
		return this.last;
	}
	
	/**
	 * Return node at given index
	 * 
	 * @param index Node index to get
	 * @return Node at given index
	 */
	protected Node getNode(int index) {
        Node node;
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
	public void insert(int index, E element) throws InvalidPositionException {
		super.validateIndex(index);

		if (index == 0)
			this.addFirst(element);
		else if (index == super.size)
			this.addLast(element);
		else {
			Node prev = this.getNode(index - 1);
			Node next = prev.getNext();
			Node toInsert = new Node(element, prev, next);
			prev.setNext(toInsert);
			next.setPrevious(toInsert);
			super.size++;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator<E>(this);
	}
	
	@Override
	public boolean remove(E element) {
		Node node = this.findNode(element);
		
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

		if (index == 0)
			return this.removeFirst();
		else if (index == super.size - 1)
			return this.removeLast();
		else {
			Node toRemove = this.getNode(index);
			this.removeNode(toRemove);
			return toRemove.getValue();
		}
	}
	
	@Override
	public E removeFirst() throws EmptyListException {
		super.validateNotEmpty();

		E element = this.first.getValue();
		this.removeFirstNode();
		return element;
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
	
	@Override
	public E removeLast() throws EmptyListException {
		super.validateNotEmpty();

		E element = this.last.getValue();
		this.removeLastNode();
		return element;
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
	protected void removeNode(Node node) {
		Node prev = node.getPrevious();
		Node next = node.getNext();
		prev.setNext(next);
		next.setPrevious(prev);
		super.size--;
	}
}
