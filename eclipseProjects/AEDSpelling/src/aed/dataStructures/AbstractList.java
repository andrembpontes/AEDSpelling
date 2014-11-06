package aed.dataStructures;

/**
 * @author Andre Pontes (42845)
 */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isValidIndex(int index){
        return index >= 0 && index < this.size();
    }

    protected void validateIndex(int index){
        if(!this.isValidIndex(index))
            throw new InvalidPositionException(index, this.size());
    }

    protected void validateNotEmpty(){
        if(this.isEmpty())
            throw new EmptyListException();
    }

    @Override
    public boolean contains(E element){
        return this.find(element) >= 0;
    }
}
