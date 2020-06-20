package nio_test;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Description: NIO实现阻塞式网络通信，服务端收到客户端请求后，给客户端反馈确认消息
 *
 * @author Baltan
 * @date 2019-03-26 15:09
 */
public class Test9 {
    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/Baltan/Desktop/1.png"),
                StandardOpenOption.READ);

        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        socketChannel.shutdownOutput();

        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, buffer.limit()));
            buffer.clear();
        }

        fileChannel.close();
        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8888));
        SocketChannel socketChannel = serverSocketChannel.accept();
        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/Baltan/Desktop/2.png"),
                StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }

        buffer.put("图片拷贝完成！".getBytes());
        buffer.flip();
        socketChannel.write(buffer);

        fileChannel.close();
        socketChannel.close();
        serverSocketChannel.close();
    }
}
