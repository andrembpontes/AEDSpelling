package aed.dataStructures.tree;

import aed.dataStructures.InsertionList;
import aed.dataStructures.LinkedList;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <E> Element
 */
class Path<E> {
    InsertionList<PathStep<E>> path;

    protected Path() {
        this.path = new LinkedList<PathStep<E>>();
    }

    /**
     * Set a new step coming from the left side
     * @param parent New last element
     */
    protected void addLeftStep(E parent) {
        this.addStep(parent, Side.LEFT);
    }

    /**
     * Set a new step coming from the right side
     * @param parent New last element
     */
    protected void addRightStep(E parent) {
        this.addStep(parent, Side.RIGHT);
    }


    /**
     * Set a new step
     * @param parent New last element
     * @param side Step side
     */
    protected void addStep(E parent, Side side) {
        this.path.add(new PathStep<E>(parent, side));
    }

    /**
     * Returns the last visited node if there is one, else returns null
     * @return The last visited node
     */
    protected E getLastParent() {
        return (this.path.size() == 0) ? null : this.path.getLast().getParent();
    }

    /**
     * Returns the side of the last descent if there is one, else returns null
     * @return The side of the last descent
     */
    protected Side getLastSide() {
        return (this.path.size() == 0) ? null :  this.path.getLast().getSide();
    }

    /**
     * Removes the last step and returns it if there is one
     * @return Last step
     */
    protected PathStep<E> removeLastStep() {
        return (this.path.size() == 0) ? null :  this.path.removeLast();
    }

    /**
     * Returns the last if there is one, else returns null
     * @return Last step
     */
    protected PathStep<E> getLastStep() {
        return (this.path.size() == 0) ? null :  this.path.getLast();
    }

    /**
     * Returns the number of steps in the path
     * @return The number of steps in the path
     */
    protected int size() {
        return this.path.size();
    }
}
