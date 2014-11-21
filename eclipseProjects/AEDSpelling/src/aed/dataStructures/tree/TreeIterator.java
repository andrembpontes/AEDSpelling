package aed.dataStructures.tree;


import aed.dataStructures.InsertionList;
import aed.dataStructures.Iterator;
import aed.dataStructures.LinkedList;
import aed.dataStructures.NoSuchElementException;

/**
 * Created by Andre on 21/11/2014.
 */
public class TreeIterator<K extends Comparable<K>, V> implements Iterator<TreeNode<K, V>> {

    private TreeNode<K, V> root;
    private InsertionList<PathStep<K, V>> path;
    private int current;
    private int size;

    public TreeIterator(TreeNode<K, V> root, int size){
        if(root == null)
            throw new NoSuchElementException();

        this.root = root;
        this.current = 0;
        this.size = size;
        this.reset();
    }

    @Override
    public void reset() {
        this.path = new LinkedList<PathStep<K, V>>();
        this.path.addFirst(new PathStep<K, V>(this.root, null));

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
    public TreeNode<K, V> next() throws NoSuchElementException {
        if(!this.hasNext()) throw new NoSuchElementException();
        PathStep<K, V> current = this.path.removeLast();

        if(current.getSide().equals(Side.LEFT)) {
            TreeNode<K, V> next = this.path.getLast().getParent().getRightNode();

            if (next != null) {
                while (next.getLeftNode() != null) {
                    next = next.getLeftNode();
                    this.path.addLast(new PathStep<K, V>(next, Side.LEFT));
                }
            }
        }
        this.current++;
        return current.getParent();
    }
}
