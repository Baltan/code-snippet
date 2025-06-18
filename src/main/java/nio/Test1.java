package nio_test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/8 16:15
 */
public class    Test1 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("/Users/Baltan/Desktop/profile.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48); // 创建容量为48字节的缓冲区
        int bytesRead = channel.read(buffer); // 从通道读取内容到缓冲区，返回值表示了有多少字节被读到了缓冲区中。如果返回-1，表示到了文件末尾
        while (bytesRead != -1) {
            System.out.println(bytesRead);
            buffer.flip(); // 缓冲区从写模式切换到读模式

            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get()); // 每次从缓冲区中读取一个字节
            }
//            buffer.clear(); // 清空整个缓冲区，让它可以再次被写入
            buffer.compact(); // 清除已经读过的数据，任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面
            bytesRead = channel.read(buffer);
        }
        file.close();
    }
}
