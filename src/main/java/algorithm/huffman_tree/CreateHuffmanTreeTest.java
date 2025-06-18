package algorithm_test.huffman_tree;

import algorithm_test.node.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-19 15:00
 */
public class CreateHuffmanTreeTest {
    public static void main(String[] args) {
        int[] arr =
                {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15,
                        35, 25, 53, 51};
        TreeNode node = createHuffmanTree(arr);
        long start = System.nanoTime();
        System.out.println(node.getData());
        System.out.println(System.nanoTime() - start);
    }

    /**
     * 创建哈夫曼树（最优二叉树）
     *
     * @param arr
     * @return
     */
    public static TreeNode createHuffmanTree(int[] arr) {

        ArrayList<TreeNode<Integer>> list = new ArrayList<>();

        for (int value : arr) {
            TreeNode<Integer> node = new TreeNode<>();
            node.setData(value);
            list.add(node);
        }

        while (list.size() > 1) {
            Collections.sort(list, Comparator.comparingInt(TreeNode::getData));

            TreeNode<Integer> left = list.get(0);
            TreeNode<Integer> right = list.get(1);
            TreeNode<Integer> node = new TreeNode<>();
            node.setData(left.getData() + right.getData());
            node.setLeft(left);
            node.setRight(right);

            list.remove(left);
            list.remove(right);
            list.add(node);
        }
        return list.get(0);
    }
}
