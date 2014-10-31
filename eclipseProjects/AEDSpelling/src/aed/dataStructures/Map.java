package aed.dataStructures;

import java.io.Serializable;


/**
 * @author am.pontes
 *
 * @param <K>
 * @param <V>
 */
public interface Map<K,V> extends Serializable
{                                                                   
    /**
     * Returs true iff the dictionary contains no entries.
     */
    boolean isEmpty( );                                           

    /**
     * Returns the number of entries in the dictionary.
     * @return
     */
    int size( );                                                  

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * returns its value; otherwise, returns null.
     * @param key
     * @return
     */
    V find( K key );                                      

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * replaces its value by the specified value and returns the old value;
     * otherwise, inserts the entry (key, value) and returns null.
     */
    V insert( K key, V value );

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
     * Returns an iterator of the entries in the dictionary.
     * @return
     */
    Iterator<Entry<K,V>> iterator( );  

} 
