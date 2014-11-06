package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <E> Type of collections elements
 */
public abstract class AbstractCollection<E> implements Collection<E> {

	private static final long serialVersionUID = 1L;
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
