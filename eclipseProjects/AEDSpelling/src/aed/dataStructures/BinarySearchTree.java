package aed.dataStructures;

public class BinarySearchTree<E extends Comparable<E>> implements Tree<E> {

    private enum Side {LEFT, RIGHT}

    private int size;

    protected class Path<E> {
        private Side side;
        private E parent;

        protected Path(E parent, Side side) {
            this.parent = parent;
            this.side = side;
        }
        protected Path() {
            this(null, null);
        }

        protected E getParent() {
            return this.parent;
        }

        public Side getDirection() {
            return this.side;
        }

        public void setLeftPath(E parent) {
            this.parent = parent;
            this.side = Side.LEFT;
        }

        public void setRightPath(E parent) {
            this.parent = parent;
            this.side = Side.RIGHT;
        }
    }

    protected TreeNode<E> root;

    public BinarySearchTree( )
    {
        root = null;
        this.size = 0;
    }

    @Override
    public V get(K key) {
        TreeNode<K,V> node = this.findNode(key);
        return node != null ? node.getValue() : null;
    }

    protected TreeNode<K, V> findNode(K key, Path<TreeNode<K, V>> path) {
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

    public V getMinimum() throws EmptyTreeException{
        return this.getLeafBySide(Side.LEFT).getValue();
    }

    public V getMaximum() throws EmptyTreeException{
        return this.getLeafBySide(Side.RIGHT).getValue();
    }

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
        Path<TreeNode<K, V>> lastStep = new Path<TreeNode<K, V>>();
        TreeNode<K,V> node = this.findNode(key, lastStep);
        if (node == null) {
            TreeNode<K,V> newLeaf = new TreeNode<K, V>(key, value);
            this.joinTrees(newLeaf, lastStep.getParent(), lastStep.getDirection());
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
        Path<TreeNode<K, V>> lastStep = new Path<TreeNode<K,V>>();
        TreeNode<K,V> node = this.findNode(key, lastStep);
        if ( node == null ) {
            return null;
        }

        V result = node.getValue();
        if (node.getLeftNode() == null) {
            this.joinTrees(node.getRightNode(), lastStep.getParent(), lastStep.getDirection());
        } else if (node.getRightNode() == null) {
            this.joinTrees(node.getLeftNode(), lastStep.getParent(), lastStep.getDirection());
        } else {
            lastStep.setRightPath(node);
            TreeNode<K, V> minNode = this.getLeafBySide(node.getRightNode(), lastStep.getDirection());
            node.setKey(minNode.getKey());
            node.setValue(minNode.getValue());
            this.joinTrees(minNode.getRightNode(), lastStep.getParent(), lastStep.getDirection());
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
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public boolean contains(Entry<K, V> element) {
        return false;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }

    @Override
    public void clear() {
        this.root = null;
    }
}