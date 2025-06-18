package nio_test;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-25 17:05
 */
public class Test5 {
    public static void main(String[] args) throws IOException {
        FileChannel inChannel =
                FileChannel.open(Paths.get("/Users/Baltan/Desktop/1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/Baltan/Desktop/2.png"),
                StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        /**
         * 内存映射文件，该缓冲区在物理内存中，非JVM中
         */
        MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        /**
         * 直接对缓冲区进行数据的读写，不需要经过通道
         */
        byte[] b = new byte[inBuffer.limit()];
        inBuffer.get(b);
        outBuffer.put(b);

        inChannel.close();
        outChannel.close();
    }
}
