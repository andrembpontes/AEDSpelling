package aed.dataStructures.tree;

import sun.security.util.Cache;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <K> Key
 * @param <V> Value
 */
public class AVLTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {

    @Override
    public V put(K key, V value) {
        Path<TreeNode<K, V>> path = new Path<TreeNode<K, V>>();
        TreeNode<K,V> node = this.findNode(key, path);
        if (node == null) {
            TreeNode<K,V> newLeaf = new AVLTreeNode<K, V>(key, value);
            this.joinTrees(newLeaf, path.getLastParent(), path.getLastSide());
            super.size++;
            this.reorganizeInsertion(path);
            return null;
        } else {
            V result = node.getValue();
            node.setValue(value);
            return result;
        }

    }

    @Override
    public V remove(K key) {
        Path<TreeNode<K, V>> path = new Path<TreeNode<K, V>>();
        int currentSize = super.size();

        V result = super.delete(key, path);

        if (super.size() < currentSize) {
            this.reorganizeRemoval(path);
        }

        return result;
    }

    /**
     * Reorganizes tree and defines new balances for the nodes after insertion
     * @param path Path used in insertion
     */
    protected void reorganizeInsertion(Path<TreeNode<K, V>> path ) {
        boolean grew = true;

        PathStep<TreeNode<K,V>> step = path.removeLastStep();
        AVLTreeNode<K,V> parent = (AVLTreeNode<K,V>) step.getParent();
        Side stepSide = step.getSide();

        while ( grew && parent != null) {
            switch (stepSide) {
                case LEFT:
                    switch (parent.getBalance()) {
                        case LEFT:
                            this.rebalaceInsertionLeft(parent, path);
                            grew = false;
                            break;
                        case EQUAL:
                            parent.setBalance(AVLNodeBalance.LEFT);
                            break;
                        case RIGHT:
                            parent.setBalance(AVLNodeBalance.EQUAL);
                            grew = false;
                            break;
                    }
                    break;
                case RIGHT:
                    switch (parent.getBalance()) {
                        case LEFT:
                            parent.setBalance(AVLNodeBalance.EQUAL);
                            grew = false;
                            break;
                        case EQUAL:
                            parent.setBalance(AVLNodeBalance.RIGHT);
                            break;
                        case RIGHT:
                            this.rebalanceInsertionRight(parent, path);
                            grew = false;
                            break;
                    }
                    break;
            }

            step = path.removeLastStep();
            parent = (AVLTreeNode<K,V>) step.getParent();
            stepSide = step.getSide();
        }
    }

    /**
     * Reorganizes tree and defines new balances for the nodes after removal
     * @param path Path used in removal
     */
    protected void reorganizeRemoval(Path<TreeNode<K, V>> path ) {
        boolean shrinked = true;

        PathStep<TreeNode<K,V>> step = path.removeLastStep();
        AVLTreeNode<K,V> parent = (AVLTreeNode<K,V>) step.getParent();
        Side stepSide = step.getSide();

        while ( shrinked && parent != null) {
            switch (stepSide) {
                case LEFT:
                    switch (parent.getBalance()) {
                        case LEFT:
                            parent.setBalance(AVLNodeBalance.EQUAL);
                            break;
                        case EQUAL:
                            parent.setBalance(AVLNodeBalance.RIGHT);
                            break;
                        case RIGHT:
                            this.rebalanceRemovalRight(parent, path);
                            break;
                    }
                    if (parent.getBalance() == AVLNodeBalance.RIGHT) {
                        shrinked = false;
                    }
                    break;
                case RIGHT:
                    switch (parent.getBalance()) {
                        case LEFT:
                            this.rebalanceRemovalLeft(parent, path);
                            break;
                        case EQUAL:
                            parent.setBalance(AVLNodeBalance.LEFT);
                            break;
                        case RIGHT:
                            parent.setBalance(AVLNodeBalance.EQUAL);
                            break;
                    }
                    if (parent.getBalance() == AVLNodeBalance.LEFT) {
                        shrinked = false;
                    }
                    break;
            }

            step = path.removeLastStep();
            parent = (AVLTreeNode<K,V>) step.getParent();
            stepSide = step.getSide();
        }
    }

    /**
     * Depending on the node balance executes a single rotation or a double
     * rotation to the left in order to keep the tree balanced after a node insertion
     * @param node child node
     * @param path Path used in insertion
     */
    protected void rebalaceInsertionLeft(AVLTreeNode<K,V> node, Path<TreeNode<K, V>> path) {
        AVLTreeNode<K,V> child = (AVLTreeNode<K,V>) node.getLeftNode();
        switch (child.getBalance() ) {
            case LEFT:
                this.rotate(node, path, Side.LEFT, false);
                break;
            // case 'E':
            //     Impossible.
            case RIGHT:
                this.rotate(node, path, Side.LEFT, true);
                break;
        }
    }

    /**
     * Depending on the node balance executes a single rotation or a double
     * rotation to the right in order to keep the tree balanced after a node insertion
     * @param node child node
     * @param path Path used in insertion
     */
    protected void rebalanceInsertionRight(AVLTreeNode<K,V> node, Path<TreeNode<K, V>> path )
    {
        AVLTreeNode<K,V> rightChild = (AVLTreeNode<K,V>) node.getRightNode();
        switch ( rightChild.getBalance() )
        {
            case LEFT:
                this.rotate(node, path, Side.RIGHT, true);
                break;
            // case 'E':
            //     Impossible.
            case RIGHT:
                this.rotate(node, path, Side.RIGHT, false);
                break;
        }
    }

    /**
     * Depending on the node balance executes a single rotation or a double
     * rotation to the left in order to keep the tree balanced after a node removal
     * @param node child node
     * @param path Path used in insertion
     */
    protected void rebalanceRemovalLeft(AVLTreeNode<K,V> node, Path<TreeNode<K, V>> path) {
        AVLTreeNode<K,V> child = (AVLTreeNode<K,V>) node.getLeftNode();
        switch (child.getBalance() ) {
            case LEFT:
                this.rotate(node, path, Side.LEFT, false);
                break;
            case EQUAL:
                this.rotate(node, path, Side.LEFT, false);
                break;
            case RIGHT:
                this.rotate(node, path, Side.LEFT, true);
                break;
        }
    }

    /**
     * Depending on the node balance executes a single rotation or a double
     * rotation to the right in order to keep the tree balanced after a node removal
     * @param node child node
     * @param path Path used in insertion
     */
    protected void rebalanceRemovalRight(AVLTreeNode<K,V> node, Path<TreeNode<K, V>> path )
    {
        AVLTreeNode<K,V> rightChild = (AVLTreeNode<K,V>) node.getRightNode();
        switch ( rightChild.getBalance() )
        {
            case LEFT:
                this.rotate(node, path, Side.RIGHT, true);
                break;
            case EQUAL:
                this.rotate(node, path, Side.RIGHT, false);
                break;
            case RIGHT:
                this.rotate(node, path, Side.RIGHT, false);
                break;
        }
    }

    /**
     * Applies a double or single rotation to a node
     * @param root Root of the rotation
     * @param path Path used to get to the root node
     * @param side Side to which the rotation will be executed
     * @param doubleRotation Flag for single and double rotation
     */
    protected void rotate(AVLTreeNode<K,V> root, Path<TreeNode<K, V>> path , Side side, boolean doubleRotation) {

        TreeNode<K,V> newSubTreeRoot = null;
        if (doubleRotation) {
            switch (side) {
                case LEFT:
                    newSubTreeRoot = doubleLeftRotation(root);
                    break;
                case RIGHT:
                    newSubTreeRoot = doubleRightRotation(root);
                    break;
            }
        } else {
            switch (side) {
                case LEFT:
                    newSubTreeRoot = singleLeftRotation(root);
                    break;
                case RIGHT:
                    newSubTreeRoot = singleRightRotation(root);
                    break;
            }
        }

        PathStep<TreeNode<K, V>> step = path.getLastStep();
        this.joinTrees(newSubTreeRoot, step.getParent(), step.getSide());
    }

    /**
     * Performs a single left rotation based on the specified node and returns its left child
     * @param root Specified node
     * @return Child node
     */
    private TreeNode<K, V> singleLeftRotation(AVLTreeNode<K, V> root) {
        AVLTreeNode<K, V> child = (AVLTreeNode<K, V>) root.getLeftNode();

        this.leftSingleRotationNewBalance(root, child);

        root.setLeftNode(child.getRightNode());
        child.setRightNode(root);
        return child;
    }

    /**
     * Performs a single right rotation based on the specified node and returns its right child
     * @param root Specified node
     * @return Child node
     */
    private TreeNode<K, V> singleRightRotation(AVLTreeNode<K, V> root) {
        AVLTreeNode<K, V> child = (AVLTreeNode<K, V>) root.getRightNode();

        this.rightSingleRotationNewBalance(root, child);

        root.setRightNode(child.getLeftNode());
        child.setLeftNode(root);
        return child;
    }

    /**
     * Performs a double left rotation based on the specified node and returns the right child of its left child
     * @param root Specified node
     * @return Child node
     */
    private TreeNode<K, V> doubleLeftRotation(AVLTreeNode<K, V> root) {
        AVLTreeNode<K, V> child = (AVLTreeNode<K, V>) root.getLeftNode();
        AVLTreeNode<K, V>  grandChild = (AVLTreeNode<K, V>) child.getRightNode();

        this.leftDoubleRotationNewBalance(root, child, grandChild);

        child.setRightNode(grandChild.getLeftNode());
        root.setLeftNode(grandChild.getRightNode());
        grandChild.setLeftNode(child);
        grandChild.setRightNode(root);
        return grandChild;
    }

    /**
     * Performs a double left rotation based on the specified node and returns the left child of its right child
     * @param root Specified node
     * @return Child node
     */
    private TreeNode<K, V> doubleRightRotation(AVLTreeNode<K, V> root) {
        AVLTreeNode<K, V>  child = (AVLTreeNode<K, V>) root.getRightNode();
        AVLTreeNode<K, V>  grandChild = (AVLTreeNode<K, V>) child.getLeftNode();

        this.rightDoubleRotationNewBalance(root, child, grandChild);

        root.setRightNode(grandChild.getLeftNode());
        child.setLeftNode(grandChild.getRightNode());
        grandChild.setLeftNode(root);
        grandChild.setRightNode(child);
        return grandChild;
    }

    /**
     * Sets new node balances for root node and its child before executing a single left rotation
     * Must be executed before the rotation occurs
     * @param root Root of the rotation
     * @param child Root node's child
     */
    private void leftSingleRotationNewBalance(AVLTreeNode<K, V> root, AVLTreeNode<K ,V> child) {
        switch (child.getBalance()) {
            case LEFT:
                root.setBalance(AVLNodeBalance.EQUAL);
                child.setBalance(AVLNodeBalance.EQUAL);
                break;
            case EQUAL:
                child.setBalance(AVLNodeBalance.RIGHT);
                break;
        }

    }

    /**
     * Sets new node balances for root node and its child before executing a single right rotation
     * Must be executed before the rotation occurs
     * @param root Root of the rotation
     * @param child Root node's child
     */
    private void rightSingleRotationNewBalance(AVLTreeNode<K, V> root, AVLTreeNode<K ,V> child) {
        switch (child.getBalance()) {
            case RIGHT:
                root.setBalance(AVLNodeBalance.EQUAL);
                child.setBalance(AVLNodeBalance.EQUAL);
                break;
            case EQUAL:
                child.setBalance(AVLNodeBalance.LEFT);
                break;
        }

    }

    /**
     * Sets new node balances for root node and its children before executing a double left rotation
     * Must be executed before the rotation occurs
     * @param root Root of the rotation
     * @param child node's child
     * @param grandChild child node's child
     */
    private void leftDoubleRotationNewBalance(AVLTreeNode<K, V> root, AVLTreeNode<K ,V> child, AVLTreeNode<K ,V> grandChild) {
        switch ( grandChild.getBalance() )
        {
            case LEFT:
                child.setBalance(AVLNodeBalance.EQUAL);
                root.setBalance(AVLNodeBalance.RIGHT);
                break;
            case EQUAL:
                child.setBalance(AVLNodeBalance.EQUAL);
                root.setBalance(AVLNodeBalance.EQUAL);
                break;
            case RIGHT:
                child.setBalance(AVLNodeBalance.LEFT);
                root.setBalance(AVLNodeBalance.EQUAL);
                break;
        }
        grandChild.setBalance(AVLNodeBalance.EQUAL);

    }

    /**
     * Sets new node balances for root node and its children before executing a double right rotation
     * Must be executed before the rotation occurs
     * @param root Root of the rotation
     * @param child node's child
     * @param grandChild child node's child
     */
    private void rightDoubleRotationNewBalance(AVLTreeNode<K, V> root, AVLTreeNode<K ,V> child, AVLTreeNode<K ,V> grandChild) {
        switch (grandChild.getBalance() )
        {
            case LEFT:
                root.setBalance(AVLNodeBalance.EQUAL);
                child.setBalance(AVLNodeBalance.RIGHT);
                break;
            case EQUAL:
                root.setBalance(AVLNodeBalance.EQUAL);
                child.setBalance(AVLNodeBalance.EQUAL);
                break;
            case RIGHT:
                root.setBalance(AVLNodeBalance.LEFT);
                child.setBalance(AVLNodeBalance.EQUAL);
                break;
        }
        grandChild.setBalance(AVLNodeBalance.EQUAL);

    }
}
