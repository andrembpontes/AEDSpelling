package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <E> Type of list elements
 */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

	private static final long serialVersionUID = 1L;

	private boolean isValidIndex(int index){
        return index >= 0 && index < this.size();
    }

    /**
     * Verifies if a list index is valid
     * @param index List index
     */
    protected void validateIndex(int index){
        if(!this.isValidIndex(index))
            throw new InvalidPositionException(index, this.size());
    }

    /**
     * Verifies if a list is not empty
     */
    protected void validateNotEmpty(){
        if(this.isEmpty())
            throw new EmptyListException();
    }

    @Override
    public boolean contains(E element){
        return this.find(element) >= 0;
    }
}
