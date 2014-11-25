package aed.dataStructures.tree;

import aed.dataStructures.InsertionList;
import aed.dataStructures.LinkedList;

/**
 * Created by gbfm on 11/25/14.
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
        this.path.add(new PathStep<E>(parent, Side.LEFT));
    }

    /**
     * Set a new step coming from the right side
     * @param parent New last element
     */
    protected void addRightStep(E parent) {
        this.path.add(new PathStep<E>(parent, Side.RIGHT));
    }

    /**
     * Returns the last visited node
     * @return The last visited node
     */
    protected E getLastParent() {
        return (this.path.size() == 0) ? null : this.path.getLast().getParent();
    }

    /**
     * Returns the side of the last descent
     * @return The side of the last descent
     */
    protected Side getLastSide() {
        return (this.path.size() == 0) ? null :  this.path.getLast().getSide();
    }

    protected PathStep<E> removeLastStep() {
        return (this.path.size() == 0) ? null :  this.path.removeLast();
    }

    protected PathStep<E> getLastStep() {
        return (this.path.size() == 0) ? null :  this.path.getLast();
    }

    protected int size() {
        return this.path.size();
    }
}
