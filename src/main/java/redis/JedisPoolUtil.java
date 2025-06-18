package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019/1/7 15:59
 */
public class JedisPoolUtil {

    private static volatile JedisPool jedisPool;

    private JedisPoolUtil() {
    }

    public static JedisPool getInstance() {
        if (jedisPool == null) {
            synchronized (JedisPoolUtil.class) {
                if (jedisPool == null) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(1000);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100 * 1000);
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
                }
            }
        }
        return jedisPool;
    }

    public static void release(Jedis jedis) {
        try {
            jedis.close();
        } catch (Exception e) {
            if (jedis != null) {
                try {
                    jedis.disconnect();
                } catch (Exception e1) {
                }
            }
        }
    }
}
