package aed.spelling;

import aed.dataStructures.InsertionList;
import aed.dataStructures.Iterator;
import aed.dataStructures.NoSuchElementException;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <E> Type of array elements
 */
class LinesIterator<E> implements Iterator<E> {

    private static final long	serialVersionUID	= 1L;

    private InsertionList<E>		list;
    private int					current, firstIndex, lastIndex;

    protected LinesIterator(InsertionList<E> list) throws InvalidLineNumberException, InvalidLineRangeException {
        this(list, 0, list.size() - 1);
    }

    protected LinesIterator(InsertionList<E> list, int firstIndex) throws InvalidLineNumberException, InvalidLineRangeException {
        this(list, firstIndex, list.size() - 1);
    }

    protected LinesIterator(InsertionList<E> list, int firstIndex, int lastIndex) throws InvalidLineNumberException, InvalidLineRangeException {

        if (!(firstIndex >= 0 && lastIndex < list.size()))
            throw new InvalidLineNumberException();

        if (lastIndex - firstIndex < 0)
            throw new InvalidLineRangeException();

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