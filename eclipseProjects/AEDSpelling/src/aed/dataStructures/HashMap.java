/**
 * 
 */
package aed.dataStructures;

/**
 * @author am.pontes
 *
 */
public class HashMap<K, V> extends AbstractMap<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private HashTable<Entry<K, V>> hashTable;

    public HashMap(){
        this.hashTable = new OpenHashTable<Entry<K, V>>();
    }

    @Override
    public boolean contains(Entry<K, V> element) {
        //TODO implement
        return false;
    }

    @Override
    public boolean containsKey(K key){
        return this.get(key) != null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }

	@Override
	public V get(K key) {
        Collection<Entry<K, V>> indexZone = this.hashTable.getHashIndex(key);
        if(indexZone == null)
            return null;

        Iterator<Entry<K,V>> iterator = indexZone.iterator();

        while(iterator.hasNext()){
            Entry<K, V> entryI = iterator.next();
            if(entryI.getKey().equals(key))
                return entryI.getValue();
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;

        if(this.containsKey(key))
            oldValue = this.remove(key);

        this.hashTable.insert(new EntryClass<K, V>(key, value));

        return oldValue;
    }

    @Override
    public V remove(K key) {
        Collection<Entry<K, V>> indexZone = this.hashTable.getHashIndex(key);
        if(indexZone == null)
            return null;

        Iterator<Entry<K,V>> iterator = indexZone.iterator();

        while(iterator.hasNext()){
            Entry<K, V> entryI = iterator.next();
            if(entryI.getKey().equals(key))
                return this.hashTable.remove(entryI).getValue();
        }

        return null;
    }


    @Override
    public Collection<K> keys() {
        //TODO implement
        return null;
    }

    @Override
    public Collection<V> values() {
        //TODO implement
        return null;
    }


}
