package aed.dataStructures;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <K> Key
 * @param <V> Value
 */
public interface Map<K,V> extends Collection<Entry<K,V>>
{

   /**
     * If there is an entry in the dictionary whose key is the specified key,
     * returns its value; otherwise, returns null.
     * @param key Key to search for
     * @return Value corresponding to the key
     */
    V get( K key );

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * replaces its value by the specified value and returns the old value;
     * otherwise, inserts the entry (key, value) and returns null.
     *
     * @param key key to add
     * @param value value to add
     * @return Old value associated with same key
     */
    V put( K key, V value );

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * removes it from the dictionary and returns its value;
     * otherwise, returns null.
     * 
     * @param key Key to remove
     * @return Value of the entry removed
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

    /**
     * Returns an iterator of the map's values
     * @return An iterator of the map's values
     */
    public Iterator<V> valuesIterator();

    /**
     * Returns an iterator of the map's keys
     * @return An iterator of the map's keys
     */
    public Iterator<K> keysIterator();
}
