package redis_test;

import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019/1/4 16:32
 */
public class HashTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        jedis.hset("h1", "k1", "v1");

        String v1 = jedis.hget("h1", "k1");
        System.out.println(v1);
        System.out.println();

        Map<String, String> map = new HashMap<>();
        map.put("k2", "v2");
        map.put("k3", "v3");
        map.put("k4", "v4");
        jedis.hmset("h1", map);
        System.out.println(jedis.hlen("h1"));
        System.out.println();

        List<String> values = jedis.hmget("h1", "k1", "k2", "k3", "k4");
        for (int i = 0, len = values.size(); i < len; i++) {
            System.out.print(values.get(i) + "\t");
        }
        System.out.println();
        System.out.println();

        Map<String, String> allValues = jedis.hgetAll("h1");
        Iterator<String> it = allValues.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.print(key + ":" + allValues.get(key));
            System.out.println();
        }
        System.out.println();

        System.out.println(jedis.hexists("h1", "k4"));
        System.out.println(jedis.hexists("h1", "k5"));
        System.out.println();

        Set<String> keySet = jedis.hkeys("h1");
        it = keySet.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        System.out.println();

        List<String> valueSet = jedis.hvals("h1");
        it = valueSet.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        System.out.println();

        jedis.hset("h2", "k1", "1");
        jedis.hincrBy("h2", "k1", 1);
        System.out.println(jedis.hget("h2", "k1"));
        jedis.hincrByFloat("h2", "k1", -3.5);
        System.out.println(jedis.hget("h2", "k1"));
    }
}
