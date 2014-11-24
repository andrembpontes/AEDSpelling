package aed.dataStructures.tree;


import aed.dataStructures.*;

/**
 * @author Andre Pontes (42845) <am.pontes@campus.fct.unl.pt>
 * @author Goncalo Marcelino (43178) <gb.marcelino@campus.fct.unl.pt>
 * @param <K> Key
 * @param <V> Value
 */
public class TreeIterator<K extends Comparable<K>, V> implements Iterator<Entry<K, V>> {

    private TreeNode<K, V> root;
    private InsertionList<PathStep<K, V>> path;
    private int current;
    private int size;

    public TreeIterator(TreeNode<K, V> root, int size){
        this.root = root;
        this.current = 0;
        this.size = size;

        if(size > 0)
            this.reset();
    }

    @Override
    public void reset() {
        this.path = new LinkedList<PathStep<K, V>>();
        this.path.addFirst(new PathStep<K, V>(this.root, Side.LEFT));

        TreeNode<K, V> current = this.path.getFirst().getParent();
        while(current.getLeftNode() != null) {
            current = current.getLeftNode();
            this.path.addLast(new PathStep<K, V>(current, Side.LEFT));
        }
    }

    @Override
    public boolean hasNext() {
        return this.current < this.size;
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if(!this.hasNext()) throw new NoSuchElementException();
        PathStep<K, V> current = this.path.removeLast();

        if(this.size - this.current > 1) {
            TreeNode<K, V> next = current.getParent().getRightNode();

            if (next != null) {
                this.path.addLast(new PathStep<K, V>(next, Side.RIGHT));
                while (next.getLeftNode() != null) {
                    next = next.getLeftNode();
                    this.path.addLast(new PathStep<K, V>(next, Side.LEFT));
                }
            }
        }

        this.current++;
        return current.getParent().getEntry();
    }
}
