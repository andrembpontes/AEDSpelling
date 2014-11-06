package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <K> Key
 * @param <V> Value
 */
public class EntryComparableByKey<K extends Comparable<K>, V> extends Entry<K, V> implements Comparable<EntryComparableByKey<K,V>> {

	private static final long serialVersionUID = 1L;

	public EntryComparableByKey(K key, V value) {
        super(key, value);
    }

    @Override
	public int compareTo(EntryComparableByKey<K, V> entry) {
        return super.getKey().compareTo(entry.getKey());
    }
}
