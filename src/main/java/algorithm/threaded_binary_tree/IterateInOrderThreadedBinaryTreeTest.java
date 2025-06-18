package algorithm.threaded_binary_tree;

import algorithm.node.ThreadedTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-18 14:56
 */
public class IterateInOrderThreadedBinaryTreeTest {
    public static void main(String[] args) {
        ThreadedTreeNode<Integer> node12 = new ThreadedTreeNode();
        node12.setData(12);

        ThreadedTreeNode<Integer> node11 = new ThreadedTreeNode();
        node11.setData(11);

        ThreadedTreeNode<Integer> node10 = new ThreadedTreeNode();
        node10.setData(10);

        ThreadedTreeNode<Integer> node9 = new ThreadedTreeNode();
        node9.setData(9);

        ThreadedTreeNode<Integer> node8 = new ThreadedTreeNode();
        node8.setData(8);

        ThreadedTreeNode<Integer> node7 = new ThreadedTreeNode();
        node7.setData(7);

        ThreadedTreeNode<Integer> node6 = new ThreadedTreeNode();
        node6.setData(6);

        ThreadedTreeNode<Integer> node5 = new ThreadedTreeNode();
        node5.setData(5);

        ThreadedTreeNode<Integer> node4 = new ThreadedTreeNode();
        node4.setData(4);

        ThreadedTreeNode<Integer> node3 = new ThreadedTreeNode();
        node3.setData(3);

        ThreadedTreeNode<Integer> node2 = new ThreadedTreeNode();
        node2.setData(2);

        ThreadedTreeNode<Integer> node1 = new ThreadedTreeNode();
        node1.setData(1);

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);
        node3.setRight(node7);

        node4.setLeft(node8);

        node5.setRight(node9);

        node6.setLeft(node10);
        node6.setRight(node11);

        node7.setRight(node12);

        CreateInOrderThreadedBinaryTreeTest.createInOrderThreadedBinaryTree(node1);

        List<Integer> list = iterateInOrderThreadedBinaryTree(node1);

        System.out.println(list);
    }

    /**
     * 遍历中序线索二叉树
     * <pre>
     *                     1
     *                  /     \
     *                2         3
     *             /  \        /  \
     *           4     5      6    7
     *         /        \    / \     \
     *        8          9 10  11     12
     *
     * 中序遍历：8, 4, 2, 5, 9, 1, 10, 6, 11, 3, 7, 12
     * </pre>
     *
     * @param node
     */
    public static List<Integer> iterateInOrderThreadedBinaryTree(ThreadedTreeNode<Integer> node) {
        if (node != null) {
            List<Integer> list = new ArrayList<>();

            while (node != null) {
                /**
                 * 找到中序遍历二叉树时的第一个节点
                 */
                while (node.getLeftPointerType() == 0) {
                    node = node.getLeft();
                }
                list.add(node.getData());
                while (node.getRightPointerType() == 1) {
                    node = node.getRight();
                    list.add(node.getData());
                }
                node = node.getRight();
            }
            return list;
        }
        return null;
    }
}
