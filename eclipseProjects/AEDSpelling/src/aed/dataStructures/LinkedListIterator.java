package aed.dataStructures;

/**
 * @author Andre Pontes (42845)
 *
 * @param <E> Type of elements in iteration
 */
class LinkedListIterator<E> implements TwoWayIterator<E>
{

    static final long serialVersionUID = 0L;

    /**
     * LinkedList of current iteration
     */
    private LinkedList<E> list;
    
    /**
     * Next node in current iteration
     */
    private LinkedList<E>.Node next;

    /**
     * Previous node in current iteration
     */
    private LinkedList<E>.Node previous;


    public LinkedListIterator(LinkedList<E> list){
        this.list = list;
    	this.next = list.getFirstNode();
        this.previous = null;
    }      
    
    @Override
    public E first(){    	
        return returnNode(this.list.getFirstNode());
    }


    @Override
    public E last(){
    	return returnNode(this.list.getLastNode()); 
    }


    @Override
    public boolean hasNext(){
        return this.next != null;
    }


    @Override
    public boolean hasPrevious(){
        return this.previous != null;
    }
    
    
    /**
     * Return node value and set previous and next pointers with correct data
     * 
     * @param node Node with value to return
     * @return Node value
     */
    private E returnNode(LinkedList<E>.Node node){
    	E toReturn = node.getValue();
    	this.previous = node.getPrevious();
    	this.next = node.getNext();
    	
    	return toReturn;
    }
    
    @Override
    public E next() throws NoSuchElementException {
        if (!this.hasNext())
            throw new NoSuchElementException();

        return returnNode(this.next);
    }

    @Override
    public E previous() throws NoSuchElementException {
        if ( !this.hasPrevious() )
            throw new NoSuchElementException();

        return returnNode(this.previous);
    }


}
