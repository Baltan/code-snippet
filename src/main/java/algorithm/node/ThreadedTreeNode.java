package algorithm.node;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-01-17 09:31
 */
public class ThreadedTreeNode<T> {
    private T data;
    private ThreadedTreeNode<T> left;
    private ThreadedTreeNode<T> right;
    /**
     * 当左右指针指向左右子树时，值为0；否则，值为1
     */
    private int leftPointerType;
    private int rightPointerType;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ThreadedTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(ThreadedTreeNode<T> left) {
        this.left = left;
    }

    public ThreadedTreeNode<T> getRight() {
        return right;
    }

    public void setRight(ThreadedTreeNode<T> right) {
        this.right = right;
    }

    public int getLeftPointerType() {
        return leftPointerType;
    }

    public void setLeftPointerType(int leftPointerType) {
        this.leftPointerType = leftPointerType;
    }

    public int getRightPointerType() {
        return rightPointerType;
    }

    public void setRightPointerType(int rightPointerType) {
        this.rightPointerType = rightPointerType;
    }

    @Override
    public String toString() {
        return "ThreadedTreeNode{" +
                "data=" + data +
                ", leftPointerType=" + leftPointerType +
                ", rightPointerType=" + rightPointerType +
                '}';
    }
}
