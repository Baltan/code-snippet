package compression_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-19 16:12
 */
public class HuffmanCodingNode {
    private Byte data;
    private int weight;
    private HuffmanCodingNode left;
    private HuffmanCodingNode right;

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public HuffmanCodingNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanCodingNode left) {
        this.left = left;
    }

    public HuffmanCodingNode getRight() {
        return right;
    }

    public void setRight(HuffmanCodingNode right) {
        this.right = right;
    }
}
