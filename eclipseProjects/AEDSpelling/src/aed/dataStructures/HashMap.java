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
	private int size;
	
	@SuppressWarnings("unchecked")
	public HashMap(){
		this.hashTable = (List<Entry<K,V>>[]) new Object[DEFAULT_HASH_TABLE_SIZE];
		this.size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public V find(K key) {
		try{
			Iterator<Entry<K,V>> iterator = this.hashTable[key.hashCode()].iterator();
			
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
		this.size++;
		return value;
	}

	private void createList(int index) {
		this.hashTable[index] = new LinkedList<Entry<K, V>>();
	}

	@Override
	public V remove(K key) {
		try{
			int hashCode = key.hashCode();
			Iterator<Entry<K, V>> iterator = this.hashTable[hashCode].iterator();
			while(iterator.hasNext()){
				Entry<K, V> entryI = iterator.next();
				if(entryI.getKey().equals(key)){
					this.hashTable[hashCode].remove(entryI);
					this.size--;
					return entryI.getValue();
				}
			}
			
			return null;
		}
		catch(NullPointerException e){
			return null;
		}
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new HashMapIterator<K, V>(this.hashTable, this.size);
	}

}
