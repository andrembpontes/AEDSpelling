package aed.dataStructures.tree;

import java.util.Comparator;

/**
 * Created by Andre on 21/11/2014.
 */
class PathStep<K extends Comparable<K>, V> {
    private Side side;
    private TreeNode<K, V> parent;

    protected PathStep(TreeNode<K, V> parent, Side side) {
        this.parent = parent;
        this.side = side;
    }

    protected PathStep() {
        this(null, null);
    }

    protected TreeNode<K, V> getParent() {
        return this.parent;
    }

    public Side getSide() {
        return this.side;
    }

    public void setLeftPath(TreeNode<K, V> parent) {
        this.parent = parent;
        this.side = Side.LEFT;
    }

    public void setRightPath(TreeNode<K, V> parent) {
        this.parent = parent;
        this.side = Side.RIGHT;
    }
}