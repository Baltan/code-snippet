package nio_test;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * Description: NIO实现非阻塞式网络通信（UDP）
 *
 * @author Baltan
 * @date 2019-03-27 16:02
 */
public class Test11 {
    @Test
    public void send() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        /**
         * 切换为非阻塞模式
         */
        datagramChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put((LocalDateTime.now().toString()).getBytes());
        buffer.flip();
        datagramChannel.send(buffer, new InetSocketAddress("127.0.0.1", 8888));
        buffer.clear();
        datagramChannel.close();
    }

    @Test
    public void receive() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        /**
         * 切换为非阻塞模式
         */
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(new InetSocketAddress(8888));

        Selector selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    datagramChannel.receive(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(), 0, buffer.limit()));
                    buffer.clear();
                }
                iterator.remove();
            }
        }
        datagramChannel.close();
    }
}
