package algorithm_test.binary_search_tree;

import algorithm_test.binary_tree.PreOrderTest;
import algorithm_test.node.TreeNode;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-21 15:11
 */
public class SearchNodeTest {
    public static void main(String[] args) {
        int[] arr = {2, 4, 10, 5, 8, 3, 9, 1, 6, 7};
        TreeNode<Integer> root = null;

        for (int value : arr) {
            TreeNode<Integer> node = new TreeNode<>();
            node.setData(value);

            root = AddNodeTest.addNode(root, node);
        }

        TreeNode<Integer> node = searchNode(root, 5);

        System.out.println(PreOrderTest.preOrder(node));
        System.out.println(node);
    }

    /**
     * 二叉查找树查找节点
     *
     * @param root
     * @param value
     * @return
     */
    public static TreeNode<Integer> searchNode(TreeNode<Integer> root, int value) {
        if (root == null) {
            return null;
        }
        if (root.getData() == value) {
            return root;
        } else if (root.getData() < value) {
            return searchNode(root.getRight(), value);
        } else {
            return searchNode(root.getLeft(), value);
        }
    }
}