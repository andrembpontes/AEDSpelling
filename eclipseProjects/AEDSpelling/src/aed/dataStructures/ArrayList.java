package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <E> Type of Array Elements
 */
public class ArrayList<E> extends AbstractList<E> implements InsertionList<E> {
	
	private static final long	serialVersionUID	= 1L;
	
	/**
	 * If not specified, array will be created with DEFAULT_START_SIZE
	 */
	private static final int	DEFAULT_START_SIZE	= 10;
	
	/**
	 * If not specified, array will grow with DEFAULT_GROWTH_TAX
	 */
	private static final int	DEFAULT_GROWTH_TAX	= 2;
	
	/**
	 * Elements array
	 */
	private E[]					array;
	

	/**
	 * Growth tax Everytime that array need more space, will grow with growthTax
	 */
	private int					growthTax;

    /**
     * Lists starting size
     */
    private int                 startSize;

	public ArrayList() {
		this(ArrayList.DEFAULT_START_SIZE, ArrayList.DEFAULT_START_SIZE);
	}
	
	public ArrayList(int startSize) {
		this(startSize, ArrayList.DEFAULT_GROWTH_TAX);
	}

	public ArrayList(int startSize, int growthTax) {
        this.startSize = startSize;
        this.growthTax = growthTax;
        this.clear();
	}
	
	@Override
	public void add(E element) {
		this.addLast(element);
	}

	@Override
	public void addFirst(E element) {
		this.insert(0, element);
	}

	@Override
	public void addLast(E element) {
		this.assureSizeToInsert();
		this.array[super.size++] = element;
	}
	
	/**
	 * Assure that array have enough space to insert 1 element
	 */
	private void assureSizeToInsert() {
		this.assureSizeToInsert(1);
	}
	
	/**
	 * Assure that array have enough space to insert specified elements
	 * 
	 * @param n Number of elements to insert
	 */
	private void assureSizeToInsert(int n) {
		while (!this.hasSizeToInsert(n))
			this.grow();
	}
	
	@Override
	public int find(E element) {
		for (int i = 0; i < this.size(); i++)
			if (this.array[i].equals(element))
				return i;
		
		return -1;
	}
	
	@Override
	public E get(int index) throws InvalidPositionException {
		super.validateIndex(index);
		return this.array[index];
	}

	@Override
	public E getFirst() throws EmptyListException {
		super.validateNotEmpty();
		return this.array[0];
	}

	@Override
	public E getLast() throws EmptyListException {
		super.validateNotEmpty();
		return this.array[this.size() - 1];
	}
	
	/**
	 * Make array grow to increase size
	 */
	private void grow() {
		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) new Object[this.array.length * this.growthTax];
		
		for (int i = 0; i < super.size; i++)
			newArray[i] = this.array[i];
		
		this.array = newArray;
	}
	
	/**
	 * Verify if array have enough space to insert n more elements
	 * 
	 * @param n Number of elements to insert
	 * @return True if as enough space. Else false.
	 */
	private boolean hasSizeToInsert(int n) {
		return this.size() + n < this.array.length;
	}

	@Override
	public void insert(int index, E element) throws InvalidPositionException {
		super.validateIndex(index);

		if (index == super.size)
			this.addLast(element);
		else {
			this.shiftDown(1, index);
			this.array[index] = element;
		}
	}

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<E>(this.array, this.size());
    }
	
	@Override
	public boolean remove(E element) {
		int i = this.find(element);
		
		if (i >= 0) {
			this.remove(i);
			return true;
		}
		
		return false;
	}
	
	@Override
	public E remove(int index) throws InvalidPositionException {
		super.validateIndex(index);
		E removed = this.array[index];
		this.shiftUp(1, index + 1);
		return removed;
	}

	@Override
	public E removeFirst() throws EmptyListException {
		super.validateNotEmpty();
        return this.remove(0);
	}

	@Override
	public E removeLast() throws EmptyListException {
		super.validateNotEmpty();
		return this.remove(super.size - 1);
	}
	
	/**
	 * Shift down array elements from specified index with specified amount. Ex: {a, b, c, d, e, f} -> shiftDown(2, 1) -> {a, b, , c, d, e, f}
	 * 
	 * @param amount Shift amount
	 * @param from Shift from that index
	 */
	private void shiftDown(int amount, int from) {
		this.assureSizeToInsert(amount);
		
		super.size += amount;
		int i = super.size - 1;
		
		while (i >= from + amount)
			this.array[i] = this.array[i-- - amount];
		
		while (i >= from)
			this.array[i--] = null;
	}
	
	/**
	 * Shift up array elements from specified index with specified amount. Ex: {a, b, c, d, e, f} -> shiftUp(2, 1) -> {a, b, d, e, f, f}
	 * 
	 * @param amount Shift amount
	 * @param from Shift from that index
	 */
	private void shiftUp(int amount, int from) {
		int i = from - amount;
		super.size -= amount;
		
		while (i < super.size)
			this.array[i] = this.array[i++ + amount];
	}

	@Override
    @SuppressWarnings("unchecked")
    public void clear() {
        this.size = 0;
        this.array = (E[]) new Object[this.startSize];
    }
}
