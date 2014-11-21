package aed.dataStructures;

/**
 * Created by gbfm on 11/21/14.
 */
public class TreeMap<K extends Comparable<K>, V> implements Map<K, V>, Tree<Entry<K, V>> {

    private Tree<Entry<K, V>> tree;



    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public Collection<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Entry<K, V> element) {
        return false;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }

    @Override
    public void clear() {

    }
}
