/**
 * 
 */
package aed.dataStructures;

/**
 * @author Andre Pontes (42845)
 *
 */
public class ArrayIterator<T> implements Iterator<T>{
	private int nElem, current;
	private T[] array;
	
	public ArrayIterator(T[] array, int nElem){
		this.nElem = nElem;
		this.array = array;
		this.current = 0;
	}
	
	public ArrayIterator(T[] array){
		this.nElem = array.length;
		this.array = array;
		this.current = 0;
	}

	@Override
	public T next() {
		return this.array[this.current++];
	}

	@Override
	public boolean hasNext() {
		return this.current < this.nElem;
	}

}
