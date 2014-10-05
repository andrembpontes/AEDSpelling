package aed.dataStructures;

public class ArrayList<E> implements List<E> {
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_START_SIZE = 10;

	private static final int GROWTH_TAX = 2;
	
	private E[] array;
	private int counter;
	
	public ArrayList(){
		this(DEFAULT_START_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int startSize){
		this.array = (E[]) new Object[startSize];
		this.counter = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return this.counter == 0;
	}

	@Override
	public int size() {
		return this.counter;
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator<E>(this.array, this.size());
	}

	@Override
	public E getFirst() throws EmptyListException {
		if(this.size() == 0)
			throw new EmptyListException();
		
		return this.array[0];
	}

	@Override
	public E getLast() throws EmptyListException {
		if(this.size() == 0)
			throw new EmptyListException();
		
		return this.array[this.size() - 1];
	}

	@Override
	public E get(int index) throws InvalidPositionException {
		return this.array[index];
	}

	@Override
	public int find(E element) {
		for(int i = 0; i < this.size(); i++)
			if(this.array[i].equals(element))
				return i;
		
		return -1;
	}

	@Override
	public void addFirst(E element) {
		this.garantSizeToInsert();
		int i = this.counter;
		
		while(i > 0)
			this.array[i] = this.array[--i];
		
		this.array[0] = element;
	}
	
	//TODO comment
	private void garantSizeToInsert(){
		this.garantSizeToInsert(1);
	}
	
	//TODO comment
	private void garantSizeToInsert(int n){
		while(!hasSizeToInsert(n)){
			this.grow();
		}
	}
	
	//TODO comment
	private void grow(){
		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) new Object[this.array.length * GROWTH_TAX];
		
		for(int i = 0; i < this.counter; i++)
			newArray[i] = this.array[i];
		
		this.array = newArray;
	}
	
	//TODO comment
	private boolean hasSizeToInsert(int n){
		return this.size() + n < this.array.length;
	}

	@Override
	public void addLast(E element) {
		this.garantSizeToInsert();
		this.array[counter++] = element;
	}
	
	@Override
	public void insert(int index, E element) throws InvalidPositionException {
		this.garantSizeToInsert();
		int i = this.counter;
		
		while(i > index)
			this.array[i] = this.array[--i];
		
		this.array[i] = element;
	}

	@Override
	public E removeFirst() throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeLast() throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(int index) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(E element) {
		// TODO Auto-generated method stub
		return false;
	}

}
