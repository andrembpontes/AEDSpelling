package aed.dataStructures;

/**
 * Created by gbfm on 11/14/14.
 */
public class TreeNode<K extends Comparable<K>, V> {

    private EntryComparableByKey<K, V> entry;
    private TreeNode<K, V> leftNode;
    private TreeNode<K, V> rightNode;

    public TreeNode(K key, V value, TreeNode<K, V> leftNode, TreeNode<K, V> rightNode) {
        this.entry = new EntryComparableByKey<K, V>(key, value);
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public TreeNode(K key, V value) {
        this(key, value, null, null);
    }

    /**
     * Returns the key of the node
     * @return the key of the node
     */
    public K getKey() {
        return entry.getKey();
    }

    /**
     * Set a new key for the node
     * @param key new key
     */
    public void setKey(K key) {
        entry.setKey(key);
    }

    /**
     * Returns the value of the node
     * @return the value of the node
     */
    public V getValue() {
        return entry.getValue();
    }

    /**
     * Set a new value for the node
     * @param value new value
     */
    public void setValue(V value) {
        entry.setValue(value);
    }

    /**
     * Returns the left node
     * @return the left node
     */
    public TreeNode<K, V> getLeftNode() {
        return leftNode;
    }

    /**
     * Returns the right node
     * @return the right node
     */
    public TreeNode<K, V> getRightNode() {
        return rightNode;
    }

    /**
     * Set a new left node
     * @param leftNode new left node
     */
    public void setLeftNode(TreeNode<K, V> leftNode) {
        this.leftNode = leftNode;
    }

    /**
     * Set a new right node
     * @param rightNode new right node
     */
    public void setRightNode(TreeNode<K, V> rightNode) {
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