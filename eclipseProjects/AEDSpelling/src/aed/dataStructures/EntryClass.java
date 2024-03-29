package aed.dataStructures;

import java.io.Serializable;
/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <K> Key
 * @param <V> Value
 */
public class EntryClass<K, V> implements Serializable, Entry<K, V>{

	private static final long serialVersionUID = 1L;

	private K key;
	private V value;
	
	public EntryClass(K key, V value){
		this.key = key;
		this.value = value;
	}

    /**
     * Returns the entry key
     * @return The entry key
     */
	public K getKey() {
		return this.key;
	}

    /**
     * Returns the entry value
     * @return The entry value
     */
	public V getValue() {
		return this.value;
	}

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
	public int hashCode(){
        return this.key.hashCode();
    }

    @Override
	public boolean equals(Object obj){
        if(obj instanceof EntryClass){
            EntryClass entry = (EntryClass) obj;
            return entry.getValue().equals(this.value) && entry.getKey().equals(this.key);
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
