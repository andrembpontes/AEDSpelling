package aed.dataStructures;

public class LinkedListIterator<T> implements Iterator<T> {

	private LinkedList<T>.Node current;
	private LinkedList<T> linkedList;
	
	LinkedListIterator(LinkedList<T> linkedList){
		this.linkedList = linkedList;
		this.current = this.linkedList.first();
	}
	
	@Override
	public T next() {
		T toReturn = (T) this.current.value();
		this.current = this.current.next();
		return toReturn;
	}

	@Override
	public boolean hasNext() {
		return this.current.hasNext();
	}

}
