package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 *
 * @param <E> Type of array elements
 */
public class ArrayIterator<E> implements Iterator<E> {

	private static final long serialVersionUID = 1L;

	private E[] array;
	private int nElements, current;

	public ArrayIterator(E[] array, int nElements) {
		this.nElements = nElements;
		this.array = array;
		this.current = 0;
	}

	@Override
	public E first() {
		return this.array[this.current = 0];
	}

	@Override
	public boolean hasNext() {
		return this.current < this.nElements;
	}

	@Override
	public E next() throws NoSuchElementException {
		return this.array[this.current++];
	}

}
