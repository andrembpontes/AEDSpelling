package aed.dataStructures;

/**
 * Created by gbfm on 11/19/14.
 */
public class AVLTreeNode<K extends Comparable<K>, V> extends TreeNode<K, V> {

    public enum Balance {Equal, Left, Right};

    private Balance nodeBalance;


    public AVLTreeNode( K key, V value, Balance nodeBalance, AVLTreeNode<K,V> left, AVLTreeNode<K,V> right ) {
        super(key, value, left, right);
        this.nodeBalance = nodeBalance;
    }

    public AVLTreeNode(K key, V value)
    {
        this(key, value, Balance.Equal, null, null);
    }


    public Balance getBalance() {
        return this.nodeBalance;
    }


    public void setBalance(Balance newBalance) {
        this.nodeBalance = newBalance;
    }

}
