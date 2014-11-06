package aed.dataStructures;

/**
 * @author am.pontes
 *
 * @param <K>
 * @param <V>
 */
public interface Map<K extends Comparable<K>,V> extends Collection<Entry<K,V>>
{

    //TODO:
   /**
     * If there is an entry in the dictionary whose key is the specified key,
     * returns its value; otherwise, returns null.
     * @param key
     * @return
     */
    V get( K key );

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * replaces its value by the specified value and returns the old value;
     * otherwise, inserts the entry (key, value) and returns null.
     */
    V put( K key, V value );

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * removes it from the dictionary and returns its value;
     * otherwise, returns null.
     * 
     * @param key
     * @return
     */
    V remove( K key );

    /**
     * Verifies if Map contains given key
     *
     * @param key Key to find
     * @return True if contains key, else False
     */
    boolean containsKey(K key);

    /**
     * Returns a collection with map keys
     *
     * @return Collection with map keys
     */
    Collection<K> keys();

    /**
     * Returns a collection with map values
     *
     * @return Collection with map values
     */
    Collection<V> values();
}
