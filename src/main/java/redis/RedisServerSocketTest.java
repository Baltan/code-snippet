package redis_test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description: 模拟拦截Redis客户端向Redis服务端发送的遵从RESP协议的数据
 *
 * @author Baltan
 * @date 2019-10-10 16:01
 * @see RedisSocketTest
 */
public class RedisServerSocketTest {
    public static void main(String[] args) {
        /**
         * 模拟启动一个Redis服务端，端口号为6378
         */
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        byte[] buf = new byte[1024];

        try {
            serverSocket = new ServerSocket(6378);
            socket = serverSocket.accept();
            /**
             * 获取Jedis客户端写入的数据，并在控制台输出
             */
            is = socket.getInputStream();
            is.read(buf);
            System.out.println(new String(buf));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
