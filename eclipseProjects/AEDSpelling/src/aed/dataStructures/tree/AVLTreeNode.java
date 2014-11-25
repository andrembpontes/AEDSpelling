package aed.dataStructures.tree;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <K> Key
 * @param <V> Value
 */
public class AVLTreeNode<K extends Comparable<K>, V> extends TreeNode<K, V> {

    private AVLNodeBalance nodeBalance;


    public AVLTreeNode( K key, V value, AVLNodeBalance nodeBalance, AVLTreeNode<K,V> left, AVLTreeNode<K,V> right ) {
        super(key, value, left, right);
        this.nodeBalance = nodeBalance;
    }

    public AVLTreeNode(K key, V value)
    {
        this(key, value, AVLNodeBalance.Equal, null, null);
    }

    /**
     * Returns the node's balance
     * @return The node's balance
     */
    public AVLNodeBalance getBalance() {
        return this.nodeBalance;
    }

    /**
     * Set a new balance to the node
     * @param newBalance new balance to the node
     */
    public void setBalance(AVLNodeBalance newBalance) {
        this.nodeBalance = newBalance;
    }

}
