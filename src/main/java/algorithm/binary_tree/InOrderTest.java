package algorithm.binary_tree;

import algorithm.node.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-15 16:47
 */
public class InOrderTest {
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

        System.out.println(inOrder(node1));
    }

    /**
     * 中序遍历二叉树
     *
     * @param treeNode
     * @return
     */
    public static List<Integer> inOrder(TreeNode<Integer> treeNode) {
        List<Integer> list = new ArrayList<>();

        if (treeNode != null && treeNode.getLeft() != null) {
            list.addAll(inOrder(treeNode.getLeft()));
        }
        if (treeNode != null) {
            list.add(treeNode.getData());
        }
        if (treeNode != null && treeNode.getRight() != null) {
            list.addAll(inOrder(treeNode.getRight()));
        }
        return list;
    }
}
