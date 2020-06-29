package compression_test.util;

import compression_test.exception.PasswordException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-06-29 16:02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UnzipUtil {
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
     * @param directory
     * @param password
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void unzipFiles(String source, String directory, char[] password)
            throws IOException, ClassNotFoundException, PasswordException {
        /**
         * 获得源文件的对象输入流
         */
        FileInputStream fis = new FileInputStream(source);
        ObjectInputStream ois = new ObjectInputStream(fis);
        /**
         * 读取对象的顺序必须和写入对象的顺序一致
         *
         * 读取加密密码
         */
        char[] oldPassword = (char[]) ois.readObject();
        /**
         * 如果输入的解压密码错误，直接抛出异常
         */
        if (!Arrays.equals(password, oldPassword)) {
            throw new PasswordException();
        }
        /**
         * 读取文件类型
         */
        String suffix = (String) ois.readObject();
        System.out.println("=================");
        System.out.println(suffix);
        /**
         * 读取编码后的byte数组
         */
        byte[] zipFile = (byte[]) ois.readObject();
        /**
         * 读取编码后的哈夫曼编码表
         */
        map = (Map<Byte, String>) ois.readObject();
        /**
         * 读取原文所有字节进行哈夫曼编码后的二进制字符串拼接在一起的总长度
         */
        binaryStringLength = (int) ois.readObject();

        ois.close();
        fis.close();
        /**
         * 使用指定的哈夫曼编码表进行解码
         */
        byte[] bytes = decodeHuffmanCode(zipFile, map);
        String destination;

        if (Objects.equals("undefined", suffix)) {
            destination = directory + File.separator + UUID.randomUUID();
        } else {
            destination = directory + File.separator + UUID.randomUUID() + "." + suffix;
        }
        /**
         * 创建文件输出流
         */
        FileOutputStream fos =
                new FileOutputStream(destination);
        /**
         * 将解压后的文件写到目标文件中
         */
        fos.write(bytes);

        fos.close();
    }

    /**
     * 使用指定的哈夫曼编码表进行解码
     *
     * @param huffmanCode
     * @param map
     * @return
     */
    private static byte[] decodeHuffmanCode(byte[] huffmanCode, Map<Byte, String> map) {
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
}
