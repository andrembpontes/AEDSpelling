package aed.dataStructures.tree;

import aed.dataStructures.tree.AVLTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Not my implementation, code found at http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
 */

class BTreePrinter {

    public static <K extends Comparable<K>, V> void printNode(AVLTreeNode<K, V> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static  <K extends Comparable<K>, V>  void printNodeInternal(List<AVLTreeNode<K, V>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<AVLTreeNode<K, V> > newNodes = new ArrayList<AVLTreeNode<K, V> >();
        for (AVLTreeNode<K, V>  node : nodes) {
            if (node != null) {
                System.out.print(node.getBalance());
                newNodes.add((AVLTreeNode<K,V>)node.getLeftNode());
                newNodes.add((AVLTreeNode<K,V>)node.getRightNode());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeftNode() != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).getRightNode() != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static  <K extends Comparable<K>, V>  int maxLevel(AVLTreeNode<K, V> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel((AVLTreeNode<K,V>)node.getLeftNode()), BTreePrinter.maxLevel((AVLTreeNode<K,V>)node.getRightNode())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}