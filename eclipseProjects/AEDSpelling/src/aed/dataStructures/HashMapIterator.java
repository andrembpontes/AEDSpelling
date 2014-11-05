package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public class HashMapIterator<E> implements Iterator<E> {

    private List<E>[] hashTable;
    private Iterator<E> hashIndexIterator;
    private int size, currentHashIndex, currentItem;

    public HashMapIterator(List<E>[] hashTable, int size){
        this.hashTable = hashTable;
        this.size = size;
        this.initializeIterator();
    }

    private void initializeIterator(){
        this.currentItem = 0;
        this.currentHashIndex = 0;
        this.getNextHashIndexIterator();
    }

    private boolean getNextHashIndexIterator(){
        while(this.currentHashIndex < this.hashTable.length && this.hashTable[this.currentHashIndex] == null)
            this.currentHashIndex++;

        if(this.currentHashIndex < this.hashTable.length) {
            this.hashIndexIterator = this.hashTable[this.currentHashIndex].iterator();
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
        return this.currentItem < this.size;
    }

    @Override
    public E next() throws NoSuchElementException {
        if(!this.hashIndexIterator.hasNext())
            this.getNextHashIndexIterator();

        this.currentItem++;
        return this.hashIndexIterator.next();
    }
}
