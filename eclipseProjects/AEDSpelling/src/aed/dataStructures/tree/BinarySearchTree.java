package aed.dataStructures.tree;

import aed.dataStructures.*;

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

    protected TreeNode<K, V> findNode(K key, PathStep<K,V> path) {
        TreeNode<K, V> node = this.root;
        int comparisonResult;

        while (node != null) {
            comparisonResult = node.getKey().compareTo(key);
            if (comparisonResult < 0) {
                if (path != null) path.setLeftPath(node);
                node = node.getLeftNode();
            } else if (comparisonResult > 0) {
                if (path != null) path.setRightPath(node);
                node = node.getRightNode();
            } else {
                return node;
            }
        }

        return null;
    }

    protected TreeNode<K, V> findNode(K key) {
        return this.findNode(key, null);
    }

    protected TreeNode<K, V> getChildNode(TreeNode<K, V> node, Side side) {
        switch (side) {
            case LEFT:
                return node.getLeftNode();
            case RIGHT:
                return node.getRightNode();
        }
        return null;
    }

    public V getMinimum() throws EmptyTreeException {
        return this.getLeafBySide(Side.LEFT).getValue();
    }

    public V getMaximum() throws EmptyTreeException{
        return this.getLeafBySide(Side.RIGHT).getValue();
    }

    protected TreeNode<K, V> getLeafBySide(TreeNode<K, V> base, Side side, PathStep<K, V> path) throws EmptyTreeException{
        TreeNode<K, V> node = base;

        if (super.isEmpty()) {
            throw new EmptyTreeException();
        }

        while (true) {
            TreeNode<K, V> nextNode = this.getChildNode(node, side);
            if (nextNode == null) {
                return node;
            }

            if (path != null) path.setLeftPath(node);
            node = nextNode;
        }
    }

    protected TreeNode<K, V> getLeafBySide(Side side) throws EmptyTreeException{
        return this.getLeafBySide(this.root, side, null);
    }

    protected TreeNode<K, V> getLeafBySide(TreeNode<K, V> base, Side side) throws EmptyTreeException{
        return this.getLeafBySide(base, side, null);
    }

    @Override
    public V put(K key, V value) {
        PathStep<K, V> lastStep = new PathStep<K, V>();
        TreeNode<K,V> node = this.findNode(key, lastStep);
        if (node == null) {
            TreeNode<K,V> newLeaf = new TreeNode<K, V>(key, value);
            this.joinTrees(newLeaf, lastStep.getParent(), lastStep.getSide());
            super.size++;
            return null;
        } else {
            V result = node.getValue();
            node.setValue(value);
            return result;
        }
    }

    protected void joinTrees(TreeNode<K, V> firstNode, TreeNode<K, V> secondNode, Side side) {
        if (secondNode == null) {
            root = firstNode;
        } else {
            switch (side) {
                case RIGHT:
                    secondNode.setRightNode(firstNode);
                    break;
                case LEFT:
                    secondNode.setLeftNode(firstNode);
                    break;
            }
        }
    }

    @Override
    public V remove(K key) {
        PathStep<K, V> lastStep = new PathStep<K, V>();
        TreeNode<K,V> node = this.findNode(key, lastStep);
        if ( node == null ) {
            return null;
        }

        V result = node.getValue();
        if (node.getLeftNode() == null) {
            this.joinTrees(node.getRightNode(), null, null);
        } else if (node.getRightNode() == null) {
            this.joinTrees(node.getLeftNode(), null, null);
        } else {
            lastStep.setRightPath(node);
            TreeNode<K, V> minNode = this.getLeafBySide(node.getRightNode(), lastStep.getSide());
            node.setKey(minNode.getKey());
            node.setValue(minNode.getValue());
            this.joinTrees(minNode.getLeftNode(), lastStep.getParent(), lastStep.getSide());
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
    public Collection<V> values() {
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
        return false;
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