package redis;

import redis.clients.jedis.Jedis;

/**
 * Description: 模拟拦截Redis客户端向Redis服务端发送的遵从RESP协议的数据
 *
 * @author Baltan
 * @date 2019-10-10 16:13
 * @see RedisServerSocketTest
 */
public class RedisSocketTest {
    public static void main(String[] args) {
        /**
         * Jedis客户端连接模拟启动的端口号为6378的Redis服务端，并写入数据
         */
        Jedis jedis = new Jedis("127.0.0.1", 6378);
        jedis.set("k1", "v1");
        jedis.close();
    }
}
