/**
 * 
 */
package aed.dataStructures;

/**
 * @author Andre Pontes (42845)
 */
public class LinkedList<T> implements List<T>{

	class Node{
		private Node previous, next;
		private T value;
		
		Node(T element, Node previous){
			this.value = element;
			
			this.previous = previous;
			
			if(this.previous.hasNext()){
				this.previous.next().previous = this;
				this.next = this.previous.next().previous;
				
				this.previous.next = this;
			}
			else{
				this.previous.next = this;
				this.next = null;
			}
		}
		
		T remove(){
			this.previous.next = this.next;
			this.next.previous = this.previous;
			
			return value;
		}
		
		boolean hasNext(){
			return this.next != null;
		}
		
		Node next(){
			return this.next;
		}
		
		boolean hasPrevious(){
			return this.previous != null;
		}
		
		Node previous(){
			return this.previous;
		}
		
		T value(){
			return this.value;
		}
	}
	
	private Node first, last;
	private int size;
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator<T>(this);
	}

	@Override
	public boolean add(T elem) {
		this.last = new Node(elem, this.last);
		this.size++;		
		return true;
	}

	@Override
	public T del(T elem) {
		Node toDel = this.getNode(elem);
		
		if(toDel == null)
			return null;
		
		toDel.remove();
		this.size--;
		
		return toDel.value();
	}
	
	private Node getNode(T elem){
		for(Node iNode = this.first; iNode != this.last; iNode = iNode.next())
			if(iNode.value().equals(elem))
				return iNode;
		
		return null;
	}
	
	public Node first(){
		return this.first;
	}
	
	public Node last(){
		return this.last;
	}

	@Override
	public int size() {
		return size;
	}
	
	

}
