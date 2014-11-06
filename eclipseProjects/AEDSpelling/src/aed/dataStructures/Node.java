package aed.dataStructures;

import java.io.Serializable;

/**
 * Created by gbfm on 11/5/14.
 */
class Node<E> implements Serializable {

    //static final long	serialVersionUID	= 0L;

    /**
     * Stores value
     */
    private E			value;

    /**
     * Pointer to previous node
     */
    private Node<E>		previous;

    /**
     * Pointer to next node
     */
    private Node<E>		next;

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
    public Node(E value, Node<E> previous) {
        this(value, previous, null);
    }

    /**
     * Creates a node with given previous and next node pointers
     *
     * @param value Node value
     * @param previous Pointer to previous node
     * @param next Pointer to next node
     */
    public Node(E value, Node<E> previous, Node<E> next) {
        this.value = value;
        this.previous = previous;
        this.next = next;
    }

    /**
     * Return next node
     *
     * @return Next node
     */
    public Node<E> getNext() {
        return this.next;
    }

    /**
     * Return previous node
     *
     * @return Previous node
     */
    public Node<E> getPrevious() {
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
    public Node<E> setNext(Node<E> next) {
        Node<E> oldNext = this.previous;
        this.next = next;
        return oldNext;
    }

    /**
     * Set previous node and return old previous node
     *
     * @param previous Pointer to previous node
     * @return Old previous node
     */
    public Node<E> setPrevious(Node<E> previous) {
        Node<E> oldPrevious = this.previous;
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