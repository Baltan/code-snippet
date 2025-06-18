package algorithm_test.binary_tree;

import algorithm_test.node.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-15 16:50
 */
public class PreOrderArrayTest {
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

        /**
         *                     1
         *                  /     \
         *                2         3
         *             /   \       /   \
         *           4      5     6     7
         *        /  \
         *       8    9
         *
         *  前序遍历：1, 2, 4, 8, 9, 5, 3, 6, 7
         */

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(preOrderArray(arr, 0));
    }

    /**
     * 前序遍历顺序存储的完全二叉树
     *
     * @param arr
     * @param startIndex
     * @return
     */
    public static List<Integer> preOrderArray(int[] arr, int startIndex) {
        List<Integer> list = new ArrayList<>();

        if (arr == null || arr.length == 0 || startIndex >= arr.length) {
            return list;
        }
        list.add(arr[startIndex]);
        if (startIndex * 2 + 1 < arr.length) {
            list.addAll(preOrderArray(arr, startIndex * 2 + 1));
        }
        if (startIndex * 2 + 2 < arr.length) {
            list.addAll(preOrderArray(arr, startIndex * 2 + 2));
        }
        return list;
    }
}
