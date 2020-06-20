package algorithm_test.binary_tree;

import algorithm_test.node.TreeNode;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-15 16:48
 */
public class DeleteNodeTest {
    public static void main(String[] args) {
        TreeNode<Integer> node9 = new TreeNode();
        node9.setData(9);

        TreeNode<Integer> node8 = new TreeNode();
        node8.setData(8);

        TreeNode<Integer> node7 = new TreeNode();
        node7.setData(7);

        TreeNode<Integer> node6 = new TreeNode();
        node6.setData(6);

        TreeNode<Integer> node5 = new TreeNode();
        node5.setData(5);

        TreeNode<Integer> node4 = new TreeNode();
        node4.setData(4);

        TreeNode<Integer> node3 = new TreeNode();
        node3.setData(3);

        TreeNode<Integer> node2 = new TreeNode();
        node2.setData(2);

        TreeNode<Integer> node1 = new TreeNode();
        node1.setData(1);

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);
        node3.setRight(node7);

        node6.setLeft(node8);
        node6.setRight(node9);

        TreeNode<Integer> restTreeNode = deleteNode(node1, 2);

        System.out.println(LineOrderTest.lineOrder(restTreeNode));
    }

    /**
     * 删除指定数值的节点
     *
     * @param treeNode
     * @param value
     * @return
     */
    public static TreeNode<Integer> deleteNode(TreeNode<Integer> treeNode, int value) {
        if (treeNode != null) {
            if (treeNode.getData() == value) {
                treeNode = null;
            } else {
                treeNode.setLeft(deleteNode(treeNode.getLeft(), value));
                treeNode.setRight(deleteNode(treeNode.getRight(), value));
            }
        }
        return treeNode;
    }
}
