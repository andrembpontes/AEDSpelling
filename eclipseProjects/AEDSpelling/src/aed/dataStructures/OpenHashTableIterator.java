package aed.dataStructures;

/**
 * @author Andre Pontes (42845)
 */
public class OpenHashTableIterator<E> implements Iterator<E> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Collection<E>[] hashTable;
    Iterator<E> indexIterator;                  //Iterator for iterate by different elements in same index
    int size, current, currentHashIndex;

    public OpenHashTableIterator(Collection<E>[] hashTable, int size){
        this.hashTable = hashTable;
        this.size = size;
    }

    private void initializeIterator(){
        this.current = 0;
        this.currentHashIndex = 0;
        this.getNextIndexIterator();
    }

    private boolean getNextIndexIterator(){
        while(this.currentHashIndex < this.hashTable.length && this.hashTable[this.currentHashIndex] == null)
            this.currentHashIndex++;

        if(this.currentHashIndex < this.hashTable.length) {
            this.indexIterator = this.hashTable[this.currentHashIndex].iterator();
            return true;
        }

        return false;
    }

    @Override
    public E first() {
        this.initializeIterator();
        return this.next();
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
            this.getNextIndexIterator();

        this.current++;
        return this.indexIterator.next();
    }
}
