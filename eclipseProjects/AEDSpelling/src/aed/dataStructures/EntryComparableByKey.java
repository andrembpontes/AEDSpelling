package aed.dataStructures;

/**
 * Created by gbfm on 11/6/14.
 */
public class EntryComparableByKey<K extends Comparable<K>, V> extends Entry<K, V> implements Comparable<EntryComparableByKey<K,V>> {

    public EntryComparableByKey(K key, V value) {
        super(key, value);
    }

    public int compareTo(EntryComparableByKey<K, V> entry) {
        return super.getKey().compareTo(entry.getKey());
    }
}
