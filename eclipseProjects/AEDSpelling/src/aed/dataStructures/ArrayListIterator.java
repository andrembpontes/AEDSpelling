package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <E> Type of array elements
 */
public class ArrayListIterator<E> implements Iterator<E> {

    private static final long	serialVersionUID	= 1L;

    private ArrayList<E>		list;
    private int					current, firstIndex, lastIndex;

    public ArrayListIterator(ArrayList<E> list) throws InvalidIndexException, InvalidIndexRangeException{
        this(list, 0, list.size() - 1);
    }

    public ArrayListIterator(ArrayList<E> list, int firstIndex) throws InvalidIndexException, InvalidIndexRangeException{
        this(list, firstIndex, list.size() - 1);
    }

    public ArrayListIterator(ArrayList<E> list, int firstIndex, int lastIndex) throws InvalidIndexException, InvalidIndexRangeException{

        if (!(firstIndex >= 0 && lastIndex < list.size()))
            throw new InvalidIndexException();

        if (lastIndex - firstIndex < 0)
            throw new InvalidIndexRangeException();

        this.list = list;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        this.current = this.firstIndex;
    }

    @Override
    public void reset() {
        this.current = firstIndex;
    }

    @Override
    public boolean hasNext() {
        return this.current <= lastIndex;
    }

    @Override
    public E next() throws NoSuchElementException {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        return this.list.get(this.current++);
    }

}
