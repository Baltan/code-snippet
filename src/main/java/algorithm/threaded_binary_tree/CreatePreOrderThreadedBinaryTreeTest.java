package algorithm.threaded_binary_tree;

import algorithm.node.ThreadedTreeNode;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-18 17:30
 */
public class CreatePreOrderThreadedBinaryTreeTest {

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

        createPreOrderThreadedBinaryTreeTest(node1);
    }

    /**
     * 前驱节点
     */
    private static ThreadedTreeNode<Integer> prevNode = null;

    /**
     * 创建前序线索二叉树
     *
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
     */
    public static void createPreOrderThreadedBinaryTreeTest(ThreadedTreeNode<Integer> node) {
        if (node != null) {
            /**
             * 若当前节点的左指针指向null，将当前节点的左指针指向前驱节点
             */
            if (node.getLeft() == null) {
                node.setLeft(prevNode);
                node.setLeftPointerType(1);
            }
            /**
             * 若前驱节点的右指针指向null，将前驱节点的右指针指向当前节点
             */
            if (prevNode != null && prevNode.getRight() == null) {
                prevNode.setRight(node);
                prevNode.setRightPointerType(1);
            }
            /**
             * 将当前节点作为前驱节点后，再处理左、右子树
             */
            prevNode = node;

            if (node.getLeftPointerType() == 0) {
                /**
                 * 当所有左子节点都递归处理完后，prevNode指向最后一个左子节点，
                 * prevNode的下一个节点是最后一个右子节点
                 */
                createPreOrderThreadedBinaryTreeTest(node.getLeft());
            }

            if (node.getRightPointerType() == 0) {
                /**
                 * 当一个右子节点处理完后，prevNode指向当前右子节点，
                 * prevNode的下一个节点是上一层的右子节点
                 */
                createPreOrderThreadedBinaryTreeTest(node.getRight());
            }
        }
    }
}
