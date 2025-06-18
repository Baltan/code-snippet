package algorithm.binary_search_tree;

import algorithm.binary_tree.InOrderTest;
import algorithm.binary_tree.PreOrderTest;
import algorithm.node.TreeNode;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-21 14:23
 */
public class AddNodeTest {
    public static void main(String[] args) {

        int[] arr = {2, 4, 10, 5, 8, 3, 9, 1, 6, 7};
        TreeNode<Integer> root = null;

        for (int value : arr) {
            TreeNode<Integer> node = new TreeNode<>();
            node.setData(value);

            root = addNode(root, node);
        }

        System.out.println(PreOrderTest.preOrder(root));

        TreeNode<Integer> node = new TreeNode<>();
        node.setData(11);

        root = addNode(root, node);

        System.out.println(PreOrderTest.preOrder(root));
        /**
         * 中序遍历的结果为正序排列的序列
         */
        System.out.println(InOrderTest.inOrder(root));
    }

    /**
     * 二叉查找树插入节点
     *
     * @param root
     * @param node
     * @return
     */
    public static TreeNode<Integer> addNode(TreeNode<Integer> root, TreeNode<Integer> node) {
        if (node == null) {
            return root;
        }
        if (root == null) {
            return node;
        }
        if (node.getData() < root.getData()) {
            if (root.getLeft() == null) {
                root.setLeft(node);
            } else {
                root.setLeft(addNode(root.getLeft(), node));
            }
        } else if (node.getData() > root.getData()) {
            if (root.getRight() == null) {
                root.setRight(node);
            } else {
                root.setRight(addNode(root.getRight(), node));
            }
        }
        return root;
    }
}
