package base64_test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/4/13 20:23
 */
public class Test2 {
    public static void main(String[] args) {
        String fileString = file2String("/Users/Baltan/Desktop/a.png");
        string2File(fileString);
    }

    public static String file2String(String filePath) {
        FileInputStream fis = null;
        byte[] buf = null;
        try {
            fis = new FileInputStream(filePath);
            buf = new byte[fis.available()];
            fis.read(buf);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(buf);
    }

    public static void string2File(String fileString) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(fileString);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            String filePath = "/Users/Baltan/Desktop/b.png";
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(b);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
