package aed.dataStructures;

/**
 * Created by gbfm on 11/14/14.
 */
public class TreeNode<E extends Comparable<E>> {

    private E value;
    private TreeNode<E> leftNode;
    private TreeNode<E> rightNode;

    public TreeNode(E value, TreeNode<E> leftNode, TreeNode<E> rightNode) {
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public TreeNode(E value) {
        this(value, null, null);
    }


    /**
     * Returns the left node
     * @return the left node
     */
    public TreeNode<E> getLeftNode() {
        return leftNode;
    }

    /**
     * Returns the right node
     * @return the right node
     */
    public TreeNode<E> getRightNode() {
        return rightNode;
    }

    /**
     * Set a new left node
     * @param leftNode new left node
     */
    public void setLeftNode(TreeNode<E> leftNode) {
        this.leftNode = leftNode;
    }

    /**
     * Set a new right node
     * @param rightNode new right node
     */
    public void setRightNode(TreeNode<E> rightNode) {
        this.rightNode = rightNode;
    }

    /**
     * Return true if the node is a leaf, false if not
     * @return true if the node is a leaf, false if not
     */
    public boolean isLeaf() {
        return (this.rightNode == null && this.leftNode == null);
    }
}
