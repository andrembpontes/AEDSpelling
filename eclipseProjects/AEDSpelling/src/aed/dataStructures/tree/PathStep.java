package aed.dataStructures.tree;


/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <E> Element
 */
class PathStep<E> {
    private Side side;
    private E parent;

    protected PathStep(E parent, Side side) {
        this.parent = parent;
        this.side = side;
    }

    protected PathStep() {
        this(null, null);
    }

    /**
     * Returns step node
     * @return The last visited node
     */
    protected E getParent() {
        return this.parent;
    }

    /**
     * Returns the side of the last descent
     * @return The side of the last descent
     */
    protected Side getSide() {
        return this.side;
    }

    /**
     * Set a new path coming from the left side
     * @param parent New last element
     */
    protected void setLeftPath(E parent) {
        this.parent = parent;
        this.side = Side.LEFT;
    }

    /**
     * Set a new path coming from the left side
     * @param parent New last element
     */
    protected void setRightPath(E parent) {
        this.parent = parent;
        this.side = Side.RIGHT;
    }
}