package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <K> Key
 * @param <V> Value
 */
public class OrderedLinkedMap<K extends Comparable<K>, V> implements Map<K, V> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderedLinkedList<EntryComparableByKey<K, V>> list;

    public OrderedLinkedMap() {
        list = new OrderedLinkedList<EntryComparableByKey<K, V>>();
    }

    @Override
    public V get(K key) {
        Iterator<EntryComparableByKey<K, V>> iterator = this.list.iterator();

        EntryComparableByKey<K, V> entryI;
        while(iterator.hasNext()){
            entryI = iterator.next();
            int comp = key.compareTo(entryI.getKey());

            if(comp == 0)
                return entryI.getValue();

            if(comp > 0)
                return null;
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        V old = this.remove(key);
        this.list.add(new EntryComparableByKey<K, V>(key, value));
        return old;
    }

    @Override
    public V remove(K key) {
        Iterator<EntryComparableByKey<K, V>> iterator = this.list.iterator();

        EntryComparableByKey<K, V> entryI;

        while(iterator.hasNext()){
            entryI = iterator.next();
            if(entryI.getKey().equals(key)) {
                this.list.remove(entryI);
                return entryI.getValue();
            }

            if(entryI.getKey().compareTo(key) > 0)
                return null;
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        Iterator<EntryComparableByKey<K, V>> iterator = this.list.iterator();

        EntryComparableByKey<K, V> entryI;

        while(iterator.hasNext()){
            entryI = iterator.next();
            if(entryI.getKey().equals(key)) {
                return true;
            }

            if(entryI.getKey().compareTo(key) > 0)
                return false;
        }
        return false;
    }

    @Override
    public Collection<K> keys() {
        Iterator<EntryComparableByKey<K, V>> iterator = this.list.iterator();
        List<K> keys = new LinkedList<K>();

        while(iterator.hasNext())
            keys.add(iterator.next().getKey());

        return keys;
    }

    @Override
    public Collection<V> values() {
        Iterator<EntryComparableByKey<K, V>> iterator = this.list.iterator();
        List<V> values = new LinkedList<V>();

        while(iterator.hasNext())
            values.add(iterator.next().getValue());

        return values;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Entry<K, V> element) {
        return ((List<Entry<K, V>>)(List<?>) this.list).contains(element);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<Entry<K, V>> iterator() {
        return ((List<Entry<K, V>>)(List<?>) this.list).iterator();
    }

    @Override
    public void clear() {

    }
}
