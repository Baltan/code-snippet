package charset;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Description: 《Java解惑》：解惑18
 *
 * @author Baltan
 * @date 2018/12/24 10:18
 */
public class Test2 {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        System.out.println(Charset.defaultCharset());
//        System.out.println(System.getProperty("file.encoding"));

        byte[] bytes = new byte[256];

        for (int i = 0; i < 256; i++) {
            bytes[i] = (byte) i;
        }
        /**
         * 打印出预期的0-255
         */
        String str = new String(bytes, StandardCharsets.ISO_8859_1);
        /**
         * 下面两种字符集不会打印出0-255
         */
//        String str = new String(bytes);
//        String str = new String(bytes, StandardCharsets.UTF_8);

//        System.out.println(str);

        for (int i = 0; i < str.length(); i++) {
            System.out.print((int) str.charAt(i) + "    ");
        }
    }
}
