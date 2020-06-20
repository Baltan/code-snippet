package nio_test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-26 10:52
 */
public class Test7 {
    public static void main(String[] args) throws CharacterCodingException {
        /**
         * nio支持的字符集
         */
//        Map<String, Charset> map = Charset.availableCharsets();
//        for (String key : map.keySet()) {
//            System.out.println(key + ":" + map.get(key));
//        }

        Charset cs1 = Charset.forName("UTF-8");
        CharsetEncoder encoder = cs1.newEncoder();
        CharsetDecoder decoder = cs1.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        String str = "安能摧眉折腰事权贵，使我不得开心颜。";
        charBuffer.put(str);
        charBuffer.flip();

        for (int i = 0, len = str.length(); i < len; i++) {
            System.out.println(charBuffer.get());
        }
        System.out.println("-----------------------------");

        /**
         * 重读charBuffer
         */
        charBuffer.rewind();

        ByteBuffer byteBuffer = encoder.encode(charBuffer);

        for (int i = 0, len = str.length() * 3; i < len; i++) {
            System.out.println(byteBuffer.get());
        }
        System.out.println("-----------------------------");

        /**
         * 重读byteBuffer
         */
        byteBuffer.rewind();
        CharBuffer newCharBuffer = decoder.decode(byteBuffer);

        for (int i = 0, len = str.length(); i < len; i++) {
            System.out.println(newCharBuffer.get());
        }
    }
}
