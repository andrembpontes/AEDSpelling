package aed.dataStructures;

//TODO change name...
/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */

public class EntryClass<K, V> implements Entry<K, V>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private K key;
	private V value;
	
	public EntryClass(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	@Override
	public K getKey() {
		return this.key;
	}

	@Override
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

}
