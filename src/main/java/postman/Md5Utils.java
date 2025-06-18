package postman_test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/11/30 17:59
 */
public class Md5Utils {
    private static final String[] hexDigits =
            new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    @Deprecated
    public static String getMd5Code(String str) throws NoSuchAlgorithmException {
        byte[] byteArray = str.getBytes(StandardCharsets.UTF_8);
        return md5(byteArray);
    }

    public static String md5(String str) throws NoSuchAlgorithmException {
        byte[] byteArray = str.getBytes(StandardCharsets.UTF_8);
        return md5(byteArray);
    }

    public static String md5(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] md5Bytes = md5.digest(bytes);
        StringBuilder builder = new StringBuilder();

        for (byte b : md5Bytes) {
            builder.append(hexDigits[b >>> 4 & 15]);
            builder.append(hexDigits[b & 15]);
        }
        return builder.toString();
    }
}

