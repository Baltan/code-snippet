package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019/1/7 15:56
 */
public class JedisPoolTest {
    public static void main(String[] args) {

        JedisPool jedisPool = JedisPoolUtil.getInstance();
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.set("k1", "v1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisPoolUtil.release(jedis);
        }
    }
}
