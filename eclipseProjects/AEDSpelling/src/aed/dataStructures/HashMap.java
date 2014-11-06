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
        this.clear();
    }

    @Override
    public void clear() {
        this.hashTable = new OpenHashTable<Entry<K, V>>();
        this.size = 0;
    }

    @Override
    public boolean contains(Entry<K, V> element) {
        return this.hashTable.contains(element);
    }

    @Override
    public boolean containsKey(K key){
        return this.get(key) != null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return this.hashTable.iterator();
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

        this.hashTable.insert(new Entry<K, V>(key, value));

        super.size++;
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
            if(entryI.getKey().equals(key)){
            	super.size--;
                return this.hashTable.remove(entryI).getValue();
            }
        }
        
        return null;
    }


    @Override
    public Collection<K> keys() {
        List<K> keys = new LinkedList<K>();
        Iterator<Entry<K, V>> iterator = this.iterator();

        while(iterator.hasNext())
            keys.add(iterator.next().getKey());

        return keys;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new LinkedList<V>();
        Iterator<Entry<K, V>> iterator = this.iterator();

        while(iterator.hasNext())
            values.add(iterator.next().getValue());

        return values;
    }


}
