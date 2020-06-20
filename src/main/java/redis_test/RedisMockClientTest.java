package redis_test;

import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Description: 模拟Redis客户端，向Redis服务端写入遵从RESP协议的数据
 *
 * @author Baltan
 * @date 2019-10-10 16:30
 */
public class RedisMockClientTest {
    public static void main(String[] args) {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        try {
            System.out.println(jedis.keys("*"));

            socket = new Socket("127.0.0.1", 6379);
            String command = redisCommand("SET", "k2", "v2");

            System.out.println(command);

            is = socket.getInputStream();
            os = socket.getOutputStream();
            byte[] buf = new byte[1024];
            os.write(command.getBytes());
            is.read(buf);

            System.out.println(new String(buf));

            System.out.println(jedis.keys("*"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

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

            jedis.close();
        }
    }

    public static String redisCommand(String... commands) {
        StringBuilder builder = new StringBuilder();
        builder.append("*").append(commands.length).append("\r\n");

        for (String command : commands) {
            builder.append("$").append(command.length()).append("\r\n").append(command).append("\r\n");
        }
        return builder.toString();
    }
}
