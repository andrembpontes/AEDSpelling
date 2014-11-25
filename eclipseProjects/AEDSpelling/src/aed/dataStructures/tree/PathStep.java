package aed.dataStructures.tree;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <K> Key
 * @param <V> Value
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

    /**
     * Returns the last visited node
     * @return The last visited node
     */
    protected TreeNode<K, V> getParent() {
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
    protected void setLeftPath(TreeNode<K, V> parent) {
        this.parent = parent;
        this.side = Side.LEFT;
    }

    /**
     * Set a new path coming from the left side
     * @param parent New last element
     */
    protected void setRightPath(TreeNode<K, V> parent) {
        this.parent = parent;
        this.side = Side.RIGHT;
    }
}