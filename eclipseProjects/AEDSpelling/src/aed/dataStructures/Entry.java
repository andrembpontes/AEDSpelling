package aed.dataStructures;

import java.io.Serializable;
/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <K> Key
 * @param <V> Value
 */
class Entry<K, V> implements Serializable{

	private static final long serialVersionUID = 1L;

	protected K key;
	protected V value;
	
	public Entry(K key, V value){
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

    @Override
	public int hashCode(){
        return this.key.hashCode();
    }

    @Override
	public boolean equals(Object obj){
        if(obj instanceof Entry){
            Entry entry = (Entry) obj;
            return entry.getValue().equals(this.value) && entry.getKey().equals(this.key);
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
