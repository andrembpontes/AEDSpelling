package aed.dataStructures.tree;


import aed.dataStructures.*;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <K> Key
 * @param <V> Value
 */
class TreeIterator<K extends Comparable<K>, V> implements Iterator<Entry<K, V>> {

    private TreeNode<K, V> root;
    private Path<TreeNode<K, V>> path;
    private int current;
    private int size;

    protected TreeIterator(TreeNode<K, V> root, int size){
        this.root = root;
        this.current = 0;
        this.size = size;

        if(size > 0)
            this.reset();
    }

    @Override
    public void reset() {
        this.path = new Path<TreeNode<K, V>>();
        this.path.addLeftStep(this.root);

        TreeNode<K, V> current = this.path.getLastParent();
        this.addLeftPath(current);
    }

    @Override
    public boolean hasNext() {
        return this.current < this.size;
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if(!this.hasNext()) throw new NoSuchElementException();
        PathStep<TreeNode<K, V>> current = this.path.removeLastStep();

        if(this.size - this.current > 1) {
            TreeNode<K, V> next = current.getParent().getRightNode();

            if (next != null) {
                this.path.addRightStep(next);
                this.addLeftPath(next);
            }
        }

        this.current++;
        return current.getParent().getEntry();
    }

    /**
     * Adds the entire left path to the path variable
     * @param next Node
     */
    private void addLeftPath(TreeNode<K, V> next) {
        while (next.getLeftNode() != null) {
            next = next.getLeftNode();
            this.path.addLeftStep(next);
        }
    }
}
