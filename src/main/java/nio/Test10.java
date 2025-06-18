package nio_test;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * Description: NIO实现非阻塞式网络通信（TCP）
 *
 * @author Baltan
 * @date 2019-03-26 15:39
 */
public class Test10 {
    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
        /**
         * 切换为非阻塞模式
         */
        socketChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(LocalDateTime.now().toString().getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        /**
         * 切换为非阻塞模式
         */
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8888));
        /**
         * 获取选择器
         */
        Selector selector = Selector.open();
        /**
         * 将通道注册到选择器上，配置选择键，指定要监听的事件类型：
         * SelectionKey.OP_ACCEPT
         * SelectionKey.OP_READ
         * SelectionKey.OP_WRITE
         * SelectionKey.OP_CONNECT
         * 当需要监听多种事件时，可以用"|"连接多种选择键：
         * SelectionKey.OP_READ | SelectionKey.OP_WRITE
         */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT | SelectionKey.OP_READ);
        /**
         * 轮询选择器监听的事件，该选择键对应的通道已经为IO操作准备就绪
         */
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (socketChannel.read(buffer) != -1) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, buffer.limit()));
                        buffer.clear();
                    }
                    socketChannel.close();
                } else if (selectionKey.isWritable()) {
                    // to do sth.
                } else if (selectionKey.isConnectable()) {
                    // to do sth.
                }
                /**
                 * 取消选择键
                 */
                iterator.remove();
            }
        }
        serverSocketChannel.close();
    }
}
