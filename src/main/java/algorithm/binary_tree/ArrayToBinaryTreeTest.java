package algorithm.binary_tree;

import algorithm.node.TreeNode;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-27 11:54
 */
public class ArrayToBinaryTreeTest {
    public static void main(String[] args) {

    }

    /**
     * 把数组转化成二叉树
     *
     * @param arr
     * @param index
     * @return
     */
    public static TreeNode arrayToBinaryTree(Integer[] arr, int index) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        TreeNode root = null;

        if (index < arr.length) {
            Integer value = arr[index];

            if (value == null) {
                return null;
            }

            root = new TreeNode();
            root.setData(value);
            root.setLeft(arrayToBinaryTree(arr, 2 * index + 1));
            root.setRight(arrayToBinaryTree(arr, 2 * index + 2));
            return root;
        }
        return root;
    }
}
