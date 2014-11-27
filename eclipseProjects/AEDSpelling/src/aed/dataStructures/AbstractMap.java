package aed.dataStructures;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <K> Key
 * @param <V> Value
 */
public abstract class AbstractMap<K, V> extends AbstractCollection<Entry<K, V>> implements Map<K, V>  {

	private static final long serialVersionUID = 1L;

    @Override
    public Iterator<V> valuesIterator() {
        return new Iterator<V>() {
            Iterator<Entry<K, V>> it = iterator();
            public boolean hasNext() { return it.hasNext(); }
            public V next() { return it.next().getValue();    }
            public void reset() {}
        };
    }

    @Override
    public Iterator<K> keysIterator() {
        return new Iterator<K>() {
            Iterator<Entry<K, V>> it = iterator();
            public boolean hasNext() { return it.hasNext(); }
            public K next() { return it.next().getKey();    }
            public void reset() {}
        };
    }
}
