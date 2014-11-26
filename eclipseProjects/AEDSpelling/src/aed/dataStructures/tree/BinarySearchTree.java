package aed.dataStructures.tree;

import aed.dataStructures.*;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <K> Key
 * @param <V> Value
 */
public class BinarySearchTree<K extends Comparable<K>, V> extends AbstractMap<K, V> {

    protected TreeNode<K, V> root;

    public BinarySearchTree( )
    {
        root = null;
        super.size = 0;
    }

    @Override
    public V get(K key) {
        TreeNode<K,V> node = this.findNode(key);
        return node != null ? node.getValue() : null;
    }

    /**
     * Returns the node with a given key and fills the path variable with the corresponding information
     * @param key Key to search for
     * @param path Path variable to fill
     * @return The node with a given key
     */
    protected TreeNode<K, V> findNode(K key, Path<TreeNode<K, V>> path) {
        path.addRightStep(null);

        TreeNode<K, V> node = this.root;

        while (node != null) {
            int comparisonResult = node.getKey().compareTo(key);
            if (comparisonResult < 0) {
                if (path != null) path.addRightStep(node);
                node = node.getRightNode();
            } else if (comparisonResult > 0) {
                if (path != null) path.addLeftStep(node);
                node = node.getLeftNode();
            } else {
                return node;
            }
        }

        return null;
    }

    /**
     * Returns the node with a given key
     * @param key Key to search for
     * @return The node with a given key
     */
    protected TreeNode<K, V> findNode(K key) {
        return this.findNode(key, null);
    }

    /**
     * Returns the left or right child of a node depending the specified side
     * @param node Parent Node
     * @param side Specified side
     * @return Child node based on size
     */
    protected TreeNode<K, V> getChildNode(TreeNode<K, V> node, Side side) {
        switch (side) {
            case LEFT:
                return node.getLeftNode();
            case RIGHT:
                return node.getRightNode();
        }
        return null;
    }

    /**
     * Returns the smallest member of the tree
     * @return The smallest member of the tree
     * @throws EmptyTreeException
     */
    public V getMinimum() throws EmptyTreeException {
        return this.getLeafBySide(Side.LEFT).getValue();
    }

    /**
     * Returns the bigger entry of the tree
     * @return The bigger entry of the tree
     * @throws EmptyTreeException
     */
    public V getMaximum() throws EmptyTreeException{
        return this.getLeafBySide(Side.RIGHT).getValue();
    }

    /**
     * Returns the leaf most to the right or to the left based on a root node and a specified side and fills the path variable with the corresponding information
     *
     * @param base Base node
     * @param side Specified side
     * @param path Path variable to fill
     * @return The leaf most to the right or to the left based on a root node and a specified side
     * @throws EmptyTreeException
     */
    protected TreeNode<K, V> getLeafBySide(TreeNode<K, V> base, Side side, Path<TreeNode<K, V>> path) throws EmptyTreeException{
        TreeNode<K, V> node = base;

        if (super.isEmpty()) {
            throw new EmptyTreeException();
        }

        while (true) {
            TreeNode<K, V> nextNode = this.getChildNode(node, side);
            if (nextNode == null) {
                return node;
            }

            if (path != null) path.addStep(node, side);
            node = nextNode;
        }
    }

    /**
     * Returns the leaf most to the right or to the left of the tree based on a specified side
     *
     * @param side Specified side
     * @return The leaf most to the right or to the left of the tree based on a specified side
     * @throws EmptyTreeException
     */
    protected TreeNode<K, V> getLeafBySide(Side side) throws EmptyTreeException{
        return this.getLeafBySide(this.root, side, null);
    }

    /**
     * Returns the leaf most to the right or to the left based on a root node and a specified side
     *
     * @param base Base node
     * @param side Specified side
     * @return The leaf most to the right or to the left based on a root node and a specified side
     * @throws EmptyTreeException
     */
    protected TreeNode<K, V> getLeafBySide(TreeNode<K, V> base, Side side) throws EmptyTreeException{
        return this.getLeafBySide(base, side, null);
    }

    @Override
    public V put(K key, V value) {
        Path<TreeNode<K, V>> path = new Path<TreeNode<K, V>>();
        TreeNode<K,V> node = this.findNode(key, path);
        if (node == null) {
            TreeNode<K,V> newLeaf = new TreeNode<K, V>(key, value);
            this.joinTrees(newLeaf, path.getLastParent(), path.getLastSide());
            super.size++;
            return null;
        } else {
            V result = node.getValue();
            node.setValue(value);
            return result;
        }
    }

    /**
     * Joins two trees
     * @param childNode Child node
     * @param parentNode Parent node
     * @param side Side of the parent node to join child node to
     */
    protected void joinTrees(TreeNode<K, V> childNode, TreeNode<K, V> parentNode, Side side) {
        if (parentNode == null) {
            root = childNode;
        } else {
            switch (side) {
                case RIGHT:
                    parentNode.setRightNode(childNode);
                    break;
                case LEFT:
                    parentNode.setLeftNode(childNode);
                    break;
            }
        }
    }

    @Override
    public V remove(K key) {
        Path<TreeNode<K, V>> path = new Path<TreeNode<K, V>>();
        return this.delete(key, path);
    }

    protected V delete(K key, Path<TreeNode<K, V>> path) {
        TreeNode<K,V> node = this.findNode(key, path);
        if ( node == null ) {
            return null;
        }

        V result = node.getValue();
        if (node.getLeftNode() == null) {
            this.joinTrees(node.getRightNode(), path.getLastParent(), path.getLastSide());
        } else if (node.getRightNode() == null) {
            this.joinTrees(node.getLeftNode(), path.getLastParent(), path.getLastSide());
        } else {
            path.addRightStep(node);
            TreeNode<K, V> minNode = this.getLeafBySide(node.getRightNode(), Side.LEFT, path);
            node.setKey(minNode.getKey());
            node.setValue(minNode.getValue());
            this.joinTrees(minNode.getRightNode(), path.getLastParent(), path.getLastSide());
        }
        super.size--;
        return result;
    }

    @Override
    public boolean containsKey(K key) {
        return this.get(key) != null;
    }

    @Override
    public Collection<K> keys() {
        List<K> keys = new LinkedList<K>();
        Iterator<Entry<K, V>> iterator = this.iterator();

        while(iterator.hasNext())
            keys.add(iterator.next().getKey());

        return keys;
    }

    @Override
    public Collection<V> values()  {
        List<V> values = new LinkedList<V>();
        Iterator<Entry<K, V>> iterator = this.iterator();

        while(iterator.hasNext())
            values.add(iterator.next().getValue());

        return values;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean contains(Entry<K, V> element) {
        return this.findNode(element.getKey()).getValue() == element.getValue();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new TreeIterator<K, V>(this.root, this.size);
    }

    @Override
    public void clear() {
        this.root = null;
    }
}