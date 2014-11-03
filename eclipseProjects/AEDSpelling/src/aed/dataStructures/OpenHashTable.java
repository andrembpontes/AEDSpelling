package aed.dataStructures;

/**
 * @author Andre Pontes (42845)
 */
public class OpenHashTable<E> extends AbstractCollection<E> implements HashTable<E>{

    private static final int DEFAULT_HASH_TABLE_SIZE = 1000;

    private List<E>[] hashTable;

    private static final int[] PRIMES =
            {
                    11, 19, 31, 47, 73,
                    113, 181, 277, 421, 643, 967,
                    1451, 2179, 3299, 4951, 7433,
                    11173, 16763, 25163, 37747, 56671, 85009,
                    127529, 191299, 287003, 430517, 645787, 968689,
                    1453043, 2179571, 3269377, 4904077, 7356119,
                    11034223, 16551361, 24827059, 37240597, 55860923, 83791441,
                    125687173, 188530777, 282796177, 424194271, 636291413, 954437161,
                    1431655751, 2147483647
            };

    public OpenHashTable(){
        this(DEFAULT_HASH_TABLE_SIZE);
    }

    @SuppressWarnings("unchecked")
    public OpenHashTable(int initialCapacity) {
        this.hashTable = (List<E>[]) new List<?>[this.getNexPrime(initialCapacity + 1)];
    }

    /**
     * Returns the next prime value after the specified value
     * @param value specified value
     * @return the next prime value after the specified value
     */
    private int getNexPrime(int value) {
        for (int i : PRIMES) {
            if (i > value) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Calculates the an element's hashcode
     * @param element element
     * @return the hashcode
     */
    private int getHashCode(Object element) {
        int rawCode = element.hashCode();
        return (rawCode < 0 ? -rawCode : rawCode ) % this.hashTable.length;
    }

    /**
     * Creates a list at the specified index
     * @param index index to create the list at
     */
    private void createList(int index) {
        this.hashTable[index] = new LinkedList<E>();
    }


    @Override
    public Iterator<E> iterator() {
        //TODO implement
        return null;
    }

    @Override
    public int hashTableOccupationFactor() {
        //TODO implement
        return 0;
    }

    @Override
    public int hashTableSize() {
        return this.hashTable.length;
    }

    @Override
    public E insert(E element) {
        int hashCode = this.getHashCode(element);

        if(this.hashTable[hashCode] == null)
            this.createList(hashCode);

        this.hashTable[hashCode].add(element);
        super.size++;
        return element;
    }


    @Override
    public E remove(E element) {
        int hashCode = this.getHashCode(element);
        List<E> list = this.hashTable[hashCode];
        if (list == null) {
            return null;
        }

        Iterator<E> iterator = list.iterator();

        while(iterator.hasNext()){
            E elemI = iterator.next();
            if(elemI.equals(element)){
                this.hashTable[hashCode].remove(elemI);
                super.size--;
                return elemI;
            }
        }

        return null;
    }

    @Override
    public boolean contains(E element) {
        return this.hashTable[this.getHashCode(element)].contains(element);
    }

    @Override
    public Collection<E> getHashIndex(Object element) {
        List<E> indexZone = this.hashTable[this.getHashCode(element)];
        if(indexZone == null || indexZone.isEmpty())
            return null;

        return indexZone;
    }
}
