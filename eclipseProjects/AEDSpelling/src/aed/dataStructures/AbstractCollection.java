package aed.dataStructures;

/**
 * @author Andre Pontes (42845)
 */
public abstract class AbstractCollection<E> implements Collection<E> {

    protected int size;

    public AbstractCollection(){
        this.size = 0;
    }

    @Override
    public final int size() {
        return this.size;
    }

    @Override
    public final boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull(){
        return false;
    }
}
