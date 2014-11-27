package aed.dataStructures.tree;

/**
 * @author Andre Pontes (42845) {@literal <am.pontes@campus.fct.unl.pt>}
 * @author Goncalo Marcelino (43178) {@literal <gb.marcelino@campus.fct.unl.pt>}
 * @param <K> Key
 * @param <V> Value
 */
class AVLTreeNode<K extends Comparable<K>, V> extends TreeNode<K, V> {

    private AVLNodeBalance nodeBalance;


    protected AVLTreeNode( K key, V value, AVLNodeBalance nodeBalance, AVLTreeNode<K,V> left, AVLTreeNode<K,V> right ) {
        super(key, value, left, right);
        this.nodeBalance = nodeBalance;
    }

    protected AVLTreeNode(K key, V value)
    {
        this(key, value, AVLNodeBalance.EQUAL, null, null);
    }

    /**
     * Returns the node's balance
     * @return The node's balance
     */
    protected AVLNodeBalance getBalance() {
        return this.nodeBalance;
    }

    /**
     * Set a new balance to the node
     * @param newBalance new balance to the node
     */
    protected void setBalance(AVLNodeBalance newBalance) {
        this.nodeBalance = newBalance;
    }

}
