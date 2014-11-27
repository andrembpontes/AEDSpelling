package aed.dataStructures;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <E> Elements
 */
class OpenHashTableIterator<E> implements Iterator<E> {

	private static final long serialVersionUID = 1L;
	Collection<E>[] hashTable;
    Iterator<E> indexIterator;                  //Iterator for iterate by different elements in same index
    int size, current, currentHashIndex;

    protected OpenHashTableIterator(Collection<E>[] hashTable, int size){
        this.hashTable = hashTable;
        this.size = size;
        this.reset();
    }

    /**
     * Initializes the iterator
     */
    private void initializeIterator(){
        this.current = 0;
        this.currentHashIndex = -1;
        this.getNextIndexIterator();
    }

    /**
     * Calculates the next hash index
     * @return True if there is a next hash index false if not
     */
    private boolean getNextIndexIterator(){
        do{
            this.currentHashIndex++;
        }
        while(this.currentHashIndex < this.hashTable.length && (this.hashTable[this.currentHashIndex] == null || this.hashTable[currentHashIndex].isEmpty()));

        if(this.currentHashIndex < this.hashTable.length) {
            this.indexIterator = this.hashTable[this.currentHashIndex].iterator();
            return true;
        }

        return false;
    }

    @Override
    public void reset() {
        this.initializeIterator();
    }

    @Override
    public boolean hasNext() {
        return this.current < this.size;
    }

    @Override
    public E next() throws NoSuchElementException {
        if(!hasNext())
            throw new NoSuchElementException();

        if(!this.indexIterator.hasNext())
            if(!this.getNextIndexIterator())
                throw new NoSuchElementException();

        this.current++;
        return this.indexIterator.next();
    }
}
