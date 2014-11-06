package aed.dataStructures;

/**
 * Created by gbfm on 11/6/14.
 */
public class LinkedList<E> extends AbstractLinkedList<E> implements InsertionList<E>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void addFirst(E element) {
        Node<E> toAdd = new Node<E>(element, null, this.first);

        if (this.isEmpty())
            this.last = toAdd;
        else
            this.first.setPrevious(toAdd);

        this.first = toAdd;
        super.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> toAdd = new Node<E>(element, this.last, null);

        if (this.isEmpty())
            this.first = toAdd;
        else
            this.last.setNext(toAdd);

        this.last = toAdd;
        super.size++;
    }

    @Override
    public E getFirst() throws EmptyListException {
        super.validateNotEmpty();
        return this.first.getValue();
    }

    @Override
    public E getLast() throws EmptyListException {
        validateNotEmpty();
        return this.last.getValue();
    }

    @Override
    public E removeFirst() throws EmptyListException {
        super.validateNotEmpty();

        E element = this.first.getValue();
        this.removeFirstNode();
        return element;
    }

    @Override
    public E removeLast() throws EmptyListException {
        super.validateNotEmpty();

        E element = this.last.getValue();
        this.removeLastNode();
        return element;
    }

    @Override
    public void insert(int index, E element) throws InvalidPositionException {
        super.validateIndex(index);

        if (index == 0)
            this.addFirst(element);
        else if (index == super.size)
            this.addLast(element);
        else {
            Node<E> prev = this.getNode(index - 1);
            Node<E> next = prev.getNext();
            Node<E> toInsert = new Node(element, prev, next);
            prev.setNext(toInsert);
            next.setPrevious(toInsert);
            super.size++;
        }
    }

    @Override
    public void add(E element) {
        this.addLast(element);
    }
}
