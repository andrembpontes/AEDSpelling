/**
 * 
 */
package aed.dataStructures;

/**
 * @author am.pontes
 *
 */
public class HashMap<K, V> implements Map<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_HASH_TABLE_SIZE = 1000;
	
	private List<Entry<K,V>>[] hashTable;
	private int currentSize;

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

	public HashMap(){
        this(DEFAULT_HASH_TABLE_SIZE);
	}

    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity) {
        this.hashTable = (List<Entry<K,V>>[]) new List<?>[this.getNexPrime(initialCapacity + 1)];
        this.currentSize = 0;
    }

    /**
     * Returns the next prime value after the specified value
     * @param value specified value
     * @return the next prime value after the specified value
     */
    private int getNexPrime(int value) {
        for (int i : PRIMES) {
            if (i > value) {
                return 0;
            }
        }
        return 0;
    }

    /**
     * Calculates the an key's hashcode
     * @param key key
     * @return the hashcode
     */
    private int getHashCode(K key) {
        return key.hashCode() % this.hashTable.length;
    }

	@Override
	public boolean isEmpty() {
		return this.currentSize == 0;
	}

	@Override
	public int currentSize() {
		return this.currentSize;
	}

	@Override
	public V find(K key) {
		try{
			Iterator<Entry<K,V>> iterator = this.hashTable[this.getHashCode(key)].iterator();

			while(iterator.hasNext()){
				Entry<K, V> entryI = iterator.next();
				if(entryI.getKey().equals(key))
					return entryI.getValue();
			}

			return null;
		}
		catch(NullPointerException e){
			return null;
		}

	}

	@Override
	public V insert(K key, V value) {
		int hashCode = key.hashCode();
		
		if(this.hashTable[hashCode] == null)
			this.createList(hashCode);
		
		this.hashTable[hashCode].add(new EntryClass<K,V>(key, value));
		this.currentSize++;
		return value;
	}

    /**
     * Creates a list at the specified index
     * @param index index to create the list at
     */
	private void createList(int index) {
		this.hashTable[index] = new LinkedList<Entry<K, V>>();
	}

	@Override
	public V remove(K key) {
		try{
			int hashCode = this.getHashCode(key);
			Iterator<Entry<K, V>> iterator = this.hashTable[hashCode].iterator();
			while(iterator.hasNext()){
				Entry<K, V> entryI = iterator.next();
				if(entryI.getKey().equals(key)){
					this.hashTable[hashCode].remove(entryI);
					this.currentSize--;
					return entryI.getValue();
				}
			}
			
			return null;
		}
		catch(NullPointerException e){
			return null;
		}
	}


}
