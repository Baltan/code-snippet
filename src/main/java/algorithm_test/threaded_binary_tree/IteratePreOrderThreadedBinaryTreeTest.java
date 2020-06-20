package algorithm_test.threaded_binary_tree;

import algorithm_test.node.ThreadedTreeNode;

import java.util.List;

import java.util.ArrayList;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-18 23:05
 */
public class IteratePreOrderThreadedBinaryTreeTest {
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

        CreatePreOrderThreadedBinaryTreeTest.createPreOrderThreadedBinaryTreeTest(node1);

        List<Integer> list = iteratePreOrderThreadedBinaryTree(node1);

        System.out.println(list);
    }

    /**
     * 遍历前序线索二叉树
     * <pre>
     *                     1
     *                  /     \
     *                2         3
     *             /  \        /  \
     *           4     5      6    7
     *         /        \    / \     \
     *        8          9 10  11     12
     *
     * 前序遍历：1, 2, 4, 8, 5, 9, 3, 6, 10, 11, 7, 12
     * </pre>
     *
     * @param node
     * @return
     */
    public static List<Integer> iteratePreOrderThreadedBinaryTree(ThreadedTreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        while (node != null) {

            while (node.getLeftPointerType() == 0) {
                list.add(node.getData());
                node = node.getLeft();
            }
            /**
             * 上面循环结束后，当前节点已没有左子节点，左指针指向后继结点，
             * 将当前节点的值记录下来后，处理其右指针指向的节点
             */
            list.add(node.getData());
            node = node.getRight();
        }
        return list;
    }
}
