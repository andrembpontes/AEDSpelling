package aed.dataStructures.tree;

import aed.dataStructures.EntryComparableByKey;

import java.io.Serializable;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <K> Key
 * @param <V> Value
 */
class TreeNode<K extends Comparable<K>, V> implements Serializable{

    private EntryComparableByKey<K, V> entry;
    private TreeNode<K, V> leftNode;
    private TreeNode<K, V> rightNode;

    protected TreeNode(K key, V value, TreeNode<K, V> leftNode, TreeNode<K, V> rightNode) {
        this.entry = new EntryComparableByKey<K, V>(key, value);
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    protected TreeNode(K key, V value) {
        this(key, value, null, null);
    }

    /**
     * Returns the key of the node
     * @return the key of the node
     */
    protected K getKey() {
        return entry.getKey();
    }

    /**
     * Set a new key for the node
     * @param key new key
     */
    protected void setKey(K key) {
        entry.setKey(key);
    }

    /**
     * Returns the value of the node
     * @return the value of the node
     */
    protected V getValue() {
        return entry.getValue();
    }

    /**
     * Set a new value for the node
     * @param value new value
     */
    protected void setValue(V value) {
        entry.setValue(value);
    }

    /**
     * Returns the left node
     * @return the left node
     */
    protected TreeNode<K, V> getLeftNode() {
        return leftNode;
    }

    /**
     * Returns the right node
     * @return the right node
     */
    protected TreeNode<K, V> getRightNode() {
        return rightNode;
    }

    /**
     * Returns the entry of the node
     * @return The entry
     */
    protected EntryComparableByKey<K, V> getEntry() {
        return this.entry;
    }

    /**
     * Sets a new entry in the node
     * @param entry New entry
     */
    protected void setEntry(EntryComparableByKey<K, V> entry) {
        this.entry = entry;
    }

    /**
     * Set a new left node
     * @param leftNode new left node
     */
    protected void setLeftNode(TreeNode<K, V> leftNode) {
        this.leftNode = leftNode;
    }

    /**
     * Set a new right node
     * @param rightNode new right node
     */
    protected void setRightNode(TreeNode<K, V> rightNode) {
        this.rightNode = rightNode;
    }

    /**
     * Return true if the node is a leaf, false if not
     * @return true if the node is a leaf, false if not
     */
    protected boolean isLeaf() {
        return (this.rightNode == null && this.leftNode == null);
    }
}
