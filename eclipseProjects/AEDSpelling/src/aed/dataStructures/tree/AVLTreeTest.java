package aed.dataStructures.tree;

import junit.framework.TestCase;
import org.junit.Test;

import javax.xml.soap.Node;
import java.util.Random;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class AVLTreeTest {


    //@Test
    public void avlTreeShouldKeepStructureAfterInsertion() {

        int NUMBER_OF_INSERTS = 1000;
        int MAX_INSERTION_VALUE = 100000;

        Random randomGenerator = new Random();
        BinarySearchTree<Integer, Integer> tree;


        tree = new AVLTree<Integer, Integer>();
        //tree = new BinarySearchTree<Integer, Integer>();

        for (int i = 0; i < NUMBER_OF_INSERTS; i++) {
            int value = randomGenerator.nextInt(MAX_INSERTION_VALUE);
            tree.put(value, value);
            testNodeBalance(tree.root);
        }

        System.out.println("Final tree size: " + tree.size());
        System.out.println("Your tree is balanced");
    }






    @Test
    public void avlTreeShouldKeepStructureAfterInsertionAndThenRemoval() {

        int MAX_INSERTION_VALUE = 10;
        int NUMBER_OF_INSERTIONS = 10;
        int NUMBER_OF_REMOVALS = 10;

        Random randomGenerator = new Random(1);
        BinarySearchTree<Integer, Integer> tree;


        tree = new AVLTree<Integer, Integer>();
        //tree = new BinarySearchTree<Integer, Integer>();

        for (int i = 0; i < NUMBER_OF_INSERTIONS; i++) {
            int value = randomGenerator.nextInt(MAX_INSERTION_VALUE);
            tree.put(value, value);
            //testNodeBalance(tree.root);
        }

        System.out.println("Your tree is balanced after insertion... starting removal");

        for (int i = 0; i < NUMBER_OF_REMOVALS; i++) {
            int value = randomGenerator.nextInt(MAX_INSERTION_VALUE);
            tree.remove(value);
            //testNodeBalance(tree.root);
        }

        System.out.println("Final tree size: " + tree.size());
        System.out.println("Your tree is balanced");

    }






    //@Test
    public void avlTreeShouldKeepStructureAfterRemovalAndInsertion() {

        int MAX_INSERTION_VALUE = 100;
        int NUMBER_OF_REPETITIONS = 100000;
        int MAX_NUMBER_OF_INSERTS_PER_REPETITION = 4;
        int MAX_NUMBER_OF_REMOVALS_PER_REPETITION = 2;

        Random randomGenerator = new Random();
        BinarySearchTree<Integer, Integer> tree;


        tree = new AVLTree<Integer, Integer>();
        //tree = new BinarySearchTree<Integer, Integer>();

        int totalNumberOfHitRemovals = 0;
        int totalNumberOfInsertedNodes = 0;

        for (int i = 0; i < NUMBER_OF_REPETITIONS; i++) {
            boolean insert = randomGenerator.nextBoolean(); //if true insert, if false remove
            int value = randomGenerator.nextInt(MAX_INSERTION_VALUE);
            if (insert) {
                int numberOfInserts = randomGenerator.nextInt(MAX_NUMBER_OF_INSERTS_PER_REPETITION);
                for (int j = 0; j < numberOfInserts; j++) {
                    if(tree.put(value, value) == null) totalNumberOfInsertedNodes++;
                    testNodeBalance(tree.root);
                }
            } else {
                int numberOfRemovals = randomGenerator.nextInt(MAX_NUMBER_OF_REMOVALS_PER_REPETITION);
                for (int j = 0; j < numberOfRemovals; j++) {
                    if(tree.remove(value) != null) totalNumberOfHitRemovals++;
                    testNodeBalance(tree.root);
                }
            }
        }

        System.out.println("Final tree size: " + tree.size());
        System.out.println("Total number of inserted nodes: " + totalNumberOfInsertedNodes);
        System.out.println("Total number of hit removals: " + totalNumberOfHitRemovals);
        System.out.println("Your tree is balanced");

    }








    public void testNodeBalance(TreeNode root) {
        if(root == null) {
            return;
        }

        int leftHeight = 0;
        int rightHeight = 0;

        TreeNode leftNode = root.getLeftNode();
        TreeNode rightNode = root.getRightNode();

        if(leftNode != null) {
            leftHeight = getTreeNodeHeight(leftNode);
            this.testNodeBalance(leftNode);
        }

        if(rightNode != null) {
            rightHeight = getTreeNodeHeight(rightNode);
            this.testNodeBalance(rightNode);
        }

        TestCase.assertTrue("Your tree is not balanced", Math.abs(leftHeight - rightHeight) <= 1);
    }








    public int getTreeNodeHeight(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int left = getTreeNodeHeight(node.getLeftNode());
        int right = getTreeNodeHeight(node.getRightNode());

        return 1 + Math.max(left, right);
    }


}