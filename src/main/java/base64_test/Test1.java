package base64_test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Description:
 *
 * @author Baltan
 * @date 2017/12/5 10:03
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println(getBase64("碉堡"));
        System.out.println(getString(getBase64("碉堡")));
    }

    public static String getBase64(String input) {
        byte[] bytes = null;
        String output = null;
        try {
            bytes = input.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (bytes != null) {
            output = new BASE64Encoder().encode(bytes);
        }
        return output;
    }

    public static String getString(String input) {
        byte[] bytes = null;
        String output = null;
        if (input != null) {
            try {
                bytes = new BASE64Decoder().decodeBuffer(input);
                output = new String(bytes, "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;
    }
}
