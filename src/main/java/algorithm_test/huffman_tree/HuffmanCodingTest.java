package algorithm_test.huffman_tree;

import algorithm_test.node.HuffmanCodingNode;

import java.io.*;
import java.util.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-19 16:15
 */
public class HuffmanCodingTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String str =
                "We hold these truths to be self-evident, that all men are created equal, that they are " +
                        "endowed by their Creator with certain unalienable Rights, that among these are " +
                        "Life, Liberty and the pursuit of Happiness.";
//        "can you can a can as a can canner can a can.";

        byte[] bytes = str.getBytes();

        byte[] huffmanCode = createHuffmanCoding(bytes);

        byte[] bytes1 = decodeHuffmanCode(huffmanCode, map);
        System.out.println(new String(bytes1));

        zipFiles("/Users/Baltan/Desktop/pic.png", "/Users/Baltan/Desktop/pic.zip");

        unzipFiles("/Users/Baltan/Desktop/pic.zip", "/Users/Baltan/Desktop/pic1.png");
    }

    /**
     * 哈夫曼编码表
     */
    private static Map<Byte, String> map;
    /**
     * 将原文所有字节进行哈夫曼编码后的二进制字符串拼接在一起的总长度
     */
    private static int binaryStringLength;

    /**
     * 使用哈夫曼编码解压文件
     *
     * @param source
     * @param destination
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void unzipFiles(String source, String destination)
            throws IOException, ClassNotFoundException {
        /**
         * 获得源文件的对象输入流
         */
        FileInputStream fis = new FileInputStream(source);
        ObjectInputStream ois = new ObjectInputStream(fis);
        /**
         * 读取对象的顺序必须和写入对象的顺序一致
         *
         * 读取编码后的byte数组
         */
        byte[] zipFile = (byte[]) ois.readObject();
        /**
         * 读取哈夫曼编码表
         */
        Map<Byte, String> map = (Map<Byte, String>) ois.readObject();

        ois.close();
        fis.close();
        /**
         * 使用指定的哈夫曼编码表进行解码
         */
        byte[] bytes = decodeHuffmanCode(zipFile, map);
        /**
         * 创建文件输出流
         */
        FileOutputStream fos = new FileOutputStream(destination);
        /**
         * 将解压后的文件写到目标文件中
         */
        fos.write(bytes);

        fos.close();
    }

    /**
     * 使用哈夫曼编码压缩文件
     */
    public static void zipFiles(String source, String destination) throws IOException {
        /**
         * 获得源文件的输入流
         */
        FileInputStream fis = new FileInputStream(source);
        /**
         * 创建一个和源文件大小相同的byte数组
         */
        byte[] bytes = new byte[fis.available()];
        /**
         * 将源文件读取到byte数组中
         */
        fis.read(bytes);

        fis.close();
        /**
         * 获得哈夫曼编码后的字节数组
         */
        byte[] zipFile = createHuffmanCoding(bytes);
        /**
         * 创建对象输出流
         */
        FileOutputStream fos = new FileOutputStream(destination);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        /**
         * 将编码后的byte数组和哈夫曼编码表写到目标文件中
         */
        oos.writeObject(zipFile);
        oos.writeObject(map);

        oos.close();
        fos.close();
    }

    /**
     * 使用指定的哈夫曼编码表进行解码
     *
     * @param huffmanCode
     * @param map
     * @return
     */
    public static byte[] decodeHuffmanCode(byte[] huffmanCode, Map<Byte, String> map) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0, len = huffmanCode.length; i < len; i++) {
            int value = huffmanCode[i];
            /**
             * 256的二进制表示为100000000，一个整型与100000000做或运算后，二进制表示的第9位一定为1，
             * 则最后整型的二进制表示长度至少有9位，便于后续截取8位长度的操作。
             * 否则二进制表示可能少于8位。例如：24的二进制表示为11000。
             */
            value |= 256;
            /**
             * 将字节转换成二进制字符串
             */
            String str = Integer.toBinaryString(value);
            if (i < len - 1) {
                /**
                 * 截取二进制字符串的末尾8位
                 */
                builder.append(str.substring(str.length() - 8));
            } else {
                int restLength = binaryStringLength - (len - 1) * 8;
                /**
                 * 编码后的二进制字符串最后还剩几位就截取二进制字符串的末尾几位
                 */
                builder.append(str.substring(str.length() - restLength));
            }
        }

        Map<String, Byte> dictionary = new HashMap<>();

        for (byte key : map.keySet()) {
            dictionary.put(map.get(key), key);
        }

        List<Byte> list = new ArrayList<>();

        for (int i = 0, len = builder.length(); i < len; ) {
            for (int cursor = 1; ; cursor++) {
                String key = builder.substring(i, i + cursor);

                Byte value;
                if ((value = dictionary.get(key)) != null) {
                    list.add(value);
                    i += cursor;
                    break;
                }
            }
            if (i >= len) {
                break;
            }
        }

        byte[] arr = new byte[list.size()];

        for (int i = 0, size = list.size(); i < size; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    /**
     * 获得哈夫曼编码后的字节数组
     *
     * @param bytes
     * @return
     */
    public static byte[] createHuffmanCoding(byte[] bytes) {

        /**
         * 将字节数组转化为节点list
         */
        List<HuffmanCodingNode> list = getHuffmanCodingNodeList(bytes);
        /**
         * 创建哈夫曼树
         */
        HuffmanCodingNode node = createHuffmanTree(list);
        /**
         * 创建哈夫曼编码表
         */
        map = createHuffmanCodingTable(node);
        /**
         * 进行哈夫曼编码
         */
        byte[] huffmanCode = doHuffmanCoding(map, bytes);

        return huffmanCode;
    }

    /**
     * 进行哈夫曼编码
     */
    public static byte[] doHuffmanCoding(Map<Byte, String> map, byte[] bytes) {

        StringBuilder builder = new StringBuilder();
        /**
         * 将所有字节拼接成一个二进制字符串
         */
        for (byte key : bytes) {
            builder.append(map.get(key));
        }
        /**
         * 将原文所有字节进行哈夫曼编码后的二进制字符串拼接在一起的总长度
         */
        binaryStringLength = builder.length();
        /**
         * 计算二进制字符串包含的字节数
         */
        int length = (int) Math.ceil(binaryStringLength * 1.0 / 8);

        byte[] huffmanCode = new byte[length];
        int index = 0;

        for (int i = 0; i < builder.length(); i += 8) {
            String subStr = i + 8 <= binaryStringLength ? builder.substring(i, i + 8) : builder.substring(i);
            /**
             * 将8位二进制字符串转化为一个字节
             */
            byte b = (byte) Integer.parseInt(subStr, 2);
            huffmanCode[index++] = b;
        }
        return huffmanCode;
    }

    /**
     * 创建哈夫曼编码表
     *
     * @param node
     * @return
     */
    public static Map<Byte, String> createHuffmanCodingTable(HuffmanCodingNode node) {

        Map<Byte, String> map = new HashMap<>();

        if (node != null && node.getLeft() == null && node.getRight() == null) {
            map.put(node.getData(), "");
            return map;
        }

        if (node != null) {
            Map<Byte, String> leftMap = createHuffmanCodingTable(node.getLeft());
            Map<Byte, String> rightMap = createHuffmanCodingTable(node.getRight());

            for (byte key : leftMap.keySet()) {
                map.put(key, "0" + leftMap.get(key));
            }

            for (byte key : rightMap.keySet()) {
                map.put(key, "1" + rightMap.get(key));
            }
        }

        return map;
    }

    /**
     * 创建哈夫曼树
     *
     * @param list
     * @return
     */
    public static HuffmanCodingNode createHuffmanTree(List<HuffmanCodingNode> list) {

        while (list.size() > 1) {
            /**
             * 将所有节点按照节点的权升序排序
             */
            Collections.sort(list, Comparator.comparingInt(HuffmanCodingNode::getWeight));

            HuffmanCodingNode left = list.get(0);
            HuffmanCodingNode right = list.get(1);
            HuffmanCodingNode node = new HuffmanCodingNode();
            node.setWeight(left.getWeight() + right.getWeight());
            node.setLeft(left);
            node.setRight(right);

            list.remove(left);
            list.remove(right);
            list.add(node);
        }
        return list.get(0);
    }

    /**
     * 将字节数组转化为节点list
     *
     * @param bytes
     * @return
     */
    public static List<HuffmanCodingNode> getHuffmanCodingNodeList(byte[] bytes) {

        List<HuffmanCodingNode> list = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();

        /**
         * 统计每个字节出现的次数
         */
        for (byte value : bytes) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }

        for (byte key : map.keySet()) {
            int value = map.get(key);
            HuffmanCodingNode node = new HuffmanCodingNode();
            node.setData(key);
            node.setWeight(value);
            list.add(node);
        }

        return list;
    }
}