package nio_test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description: 通道基本操作
 *
 * @author Baltan
 * @date 2019-03-20 16:42
 */
public class Test4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/Baltan/Desktop/1.png");
        FileOutputStream fos = new FileOutputStream("/Users/Baltan/Desktop/2.png");
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (inChannel.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        fis.close();
        fos.close();
        inChannel.close();
        outChannel.close();
    }
}
