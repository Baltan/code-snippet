package algorithm.binary_search_tree;

import algorithm.binary_tree.PreOrderTest;
import algorithm.node.TreeNode;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-21 15:31
 */
public class DeleteNodeTest {
    public static void main(String[] args) {
        int[] arr = {2, 4, 10, 5, 8, 3, 9, 1, 6, 7};
        TreeNode<Integer> root = null;

        for (int value : arr) {
            TreeNode<Integer> node = new TreeNode<>();
            node.setData(value);

            root = AddNodeTest.addNode(root, node);
        }
        root = deleteNode(root, 8);
        System.out.println(PreOrderTest.preOrder(root));
    }

    /**
     * 二叉查找树删除节点
     *
     * @param root
     * @param value
     * @return
     */
    public static TreeNode<Integer> deleteNode(TreeNode<Integer> root, int value) {
        if (root == null) {
            return null;
        }
        /**
         * 查找要删除的节点
         */
        TreeNode<Integer> node = SearchNodeTest.searchNode(root, value);
        if (node == null) {
            return root;
        }
        /**
         * 查找要删除的节点的父节点
         */
        TreeNode<Integer> parent = searchParentNode(root, value);

        if (node.getLeft() == null && node.getRight() == null) {
            /**
             * 删除的节点为叶节点
             */
            if (parent == null) {
                root = null;
            } else {
                if (parent.getLeft() != null && parent.getLeft().getData() == value) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
        } else if (node.getLeft() != null && node.getRight() == null) {
            /**
             * 删除的节点只有左子树
             */
            if (parent == null) {
                root = root.getLeft();
            } else {
                if (parent.getLeft() != null && parent.getLeft().getData() == value) {
                    parent.setLeft(node.getLeft());
                } else {
                    parent.setRight(node.getLeft());
                }
            }
        } else if (node.getRight() != null && node.getLeft() == null) {
            /**
             * 删除的节点只有右子树
             */
            if (parent == null) {
                root = root.getRight();
            } else {
                if (parent.getLeft() != null && parent.getLeft().getData() == value) {
                    parent.setLeft(node.getRight());
                } else {
                    parent.setRight(node.getRight());
                }
            }
        } else {
            /**
             * 删除节点既有左子树，又有右子树
             */
            int minValue = deleteMinNode(root, node.getRight());
            node.setData(minValue);
        }
        return root;
    }

    /**
     * 二叉查找树删除权最小的节点
     *
     * @param root
     * @param subRoot
     * @return
     */
    private static int deleteMinNode(TreeNode<Integer> root, TreeNode<Integer> subRoot) {
        TreeNode<Integer> node = subRoot;
        /**
         * 找到二叉查找树中权最小的节点
         */
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        /**
         * 最小权节点不可能有左子树，因为左节点的权会比该节点的权更小
         *
         * 若最小权节点没有右子树，则该节点为叶节点，并且只可能为其双亲节点的左节点，因为权小于双亲节点的权
         *
         *         TreeNode<Integer> parent = searchParentNode(root, node.getData());
         *
         *         if (node.getRight() == null) {
         *             parent.setLeft(null);
         *         } else {
         *             parent.setLeft(node.getRight());
         *         }
         */
        /**
         * 若最小权节点的权等于子树根节点的值，说明最小权节点就是子树的根节点，且子树没有左子树，
         * 否则左子树上节点的值小于根节点的权
         */
        if (subRoot.getData() == node.getData()) {
            TreeNode<Integer> parent = searchParentNode(root, node.getData());
            parent.setRight(node.getRight());
        } else {
            deleteNode(subRoot, node.getData());
        }
        return node.getData();
    }

    /**
     * 二叉查找树查找给定节点的父节点
     *
     * @param root
     * @param value
     * @return
     */
    private static TreeNode<Integer> searchParentNode(TreeNode<Integer> root, int value) {
        if (root == null) {
            return null;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return null;
        } else if (value < root.getData()) {
            if (root.getLeft() == null) {
                return null;
            } else {
                if (root.getLeft() != null && root.getLeft().getData() == value) {
                    return root;
                } else {
                    return searchParentNode(root.getLeft(), value);
                }
            }
        } else if (value > root.getData()) {
            if (root.getRight() == null) {
                return null;
            } else {
                if (root.getRight() != null && root.getRight().getData() == value) {
                    return root;
                } else {
                    return searchParentNode(root.getRight(), value);
                }
            }
        }
        return null;
    }
}
