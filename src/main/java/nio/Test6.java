package nio;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-25 17:43
 */
public class Test6 {
    public static void main(String[] args) throws IOException {
        FileChannel inChannel =
                FileChannel.open(Paths.get("/Users/Baltan/Desktop/1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/Baltan/Desktop/2.png"),
                StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        /**
         * 通道之间数据传输，利用直接缓冲区
         */
//        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }
}
