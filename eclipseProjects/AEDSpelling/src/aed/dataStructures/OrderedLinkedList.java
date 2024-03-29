package aed.dataStructures;


/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <E> Type of List Elements
 */
public class OrderedLinkedList<E extends Comparable<E>> extends LinkedList<E> implements List<E> {

	private static final long serialVersionUID = 1L;

	public OrderedLinkedList() {
        clear();
    }

    @Override
    public void add(E element) {
        if (super.isEmpty()) {
            super.addFirst(element);
        }

        int index = 0;

        for (Iterator<E> iterator = super.iterator(); iterator.hasNext();) {
            E nextElement = iterator.next();
            if (element.compareTo(nextElement) > 0) {
                break;
            }
            index++;
        }

        super.insert(index, element);
    }

    @Override
    public int find(E element) {
        int i = 0;
        for (Iterator<E> iterator = super.iterator(); iterator.hasNext();) {
            i++;
            E nextElement = iterator.next();
            if(element.equals(nextElement)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the entry with the largest value in the dictionary.
     * @return the entry with the largest value in the dictionary.
     */
    public E getBigger() {
        return super.getLast();
    }

    /**
     * Returns the entry with the smallest value in the dictionary.
     * @return the entry with the smallest value in the dictionary.
     */
    public E getSmaller() {
        return super.getFirst();
    }
}
