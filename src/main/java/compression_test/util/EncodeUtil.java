package compression_test.util;

import compression_test.pojo.HuffmanCodingNode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-06-29 16:01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EncodeUtil {
    /**
     * 哈夫曼编码表
     */
    private static Map<Byte, String> map;
    /**
     * 将原文所有字节进行哈夫曼编码后的二进制字符串拼接在一起的总长度
     */
    private static int binaryStringLength;

    /**
     * 使用哈夫曼编码压缩文件
     *
     * @param source
     * @param destination
     * @param password
     * @throws IOException
     */
    public static void encode(String source, String destination, char[] password) throws IOException {
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
        /**
         * 文件后缀
         */
        String suffix;
        int dotIndex = source.lastIndexOf(".");

        if (dotIndex != -1) {
            suffix = source.substring(dotIndex);
        } else {
            suffix = "undefined";
        }
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
         * 将加密密码写到目标文件中
         */
        oos.writeObject(password);
        /**
         * 将文件类型写到目标文件中
         */
        oos.writeObject(suffix);
        /**
         * 将编码后的byte数组写到目标文件中
         */
        oos.writeObject(zipFile);
        /**
         * 将编码后的哈夫曼编码表写到目标文件中
         */
        oos.writeObject(map);
        /**
         * 将原文所有字节进行哈夫曼编码后的二进制字符串拼接在一起的总长度写到目标文件中
         */
        oos.writeObject(binaryStringLength);

        StreamUtil.close(fis);
        StreamUtil.close(oos);
        StreamUtil.close(fos);
    }

    /**
     * 获得哈夫曼编码后的字节数组
     *
     * @param bytes
     * @return
     */
    private static byte[] createHuffmanCoding(byte[] bytes) {
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
     * 将字节数组转化为节点list
     *
     * @param bytes
     * @return
     */
    private static List<HuffmanCodingNode> getHuffmanCodingNodeList(byte[] bytes) {
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

    /**
     * 创建哈夫曼树
     *
     * @param list
     * @return
     */
    private static HuffmanCodingNode createHuffmanTree(List<HuffmanCodingNode> list) {
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
     * 创建哈夫曼编码表
     *
     * @param node
     * @return
     */
    private static Map<Byte, String> createHuffmanCodingTable(HuffmanCodingNode node) {
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
     * 进行哈夫曼编码
     */
    private static byte[] doHuffmanCoding(Map<Byte, String> map, byte[] bytes) {
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
}
