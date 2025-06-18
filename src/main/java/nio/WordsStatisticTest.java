package nio_test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/9 12:56
 */
public class WordsStatisticTest {
    public static void main(String[] args) throws Exception {
        SortedMap<String, Integer> wordMap = new TreeMap<>();
        String filePath = "/Users/Baltan/Desktop/a.txt";
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int val = channel.read(buf);
        StringBuilder builder = new StringBuilder();
        while (val != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
                int code = buf.get();
                if ((code >= 65 && code <= 90) || (code >= 97 && code <= 122) || code == 45) {
                    builder.append((char) code);
                } else {
                    String word = builder.toString().toLowerCase();
                    if (!word.trim().equals("") && wordMap.containsKey(word)) {
                        wordMap.put(word, wordMap.get(word) + 1);
                    } else if (!word.trim().equals("") && !wordMap.containsKey(word)) {
                        wordMap.put(word, 1);
                    }
                    builder.delete(0, builder.length());
                }
            }
            buf.compact();
            val = channel.read(buf);
        }
        file.close();
        int totalAmount = 0;
        Set<String> wordSet = wordMap.keySet();
        Iterator<String> it = wordSet.iterator();
        while (it.hasNext()) {
            String word = it.next();
            if (!word.trim().equals("")) {
                System.out.println("单词 " + word + " 共出现：" + wordMap.get(word) + "次");
                totalAmount += wordMap.get(word);
            }
        }
        System.out.println("_____________________________________");
        System.out.println("全文合计 " + totalAmount + " 个单词");
    }
}
