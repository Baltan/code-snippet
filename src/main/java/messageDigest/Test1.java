package messageDigest;

import java.security.MessageDigest;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/2/17 14:13
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        getMd5("新年好");
    }

    public static void getMd5(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("md5");
        md.update(text.getBytes());
        byte[] bArray1 = md.digest();
        for (byte b : bArray1) {
            System.out.print(b + "\t");
        }
        System.out.println();
        byte[] bArray2 = md.digest(text.getBytes());
        for (byte b : bArray2) {
            System.out.print(b + "\t");
        }
    }
}
