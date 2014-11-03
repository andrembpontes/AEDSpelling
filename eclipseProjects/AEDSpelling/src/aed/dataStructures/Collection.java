package aed.dataStructures;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 */
public abstract class Collection {

    protected int size = 0;

    /**
     * Verify is list is empty
     *
     * @return True if list is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns list size
     *
     * @return Size
     */
    public int size() {
        return this.size;
    }


}
