package redis_test;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019/1/4 15:26
 */
public class StringTest {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        jedis.set("k1", "v1");
        jedis.set("k2", "2");
        jedis.set("k3", "3");
        System.out.println(jedis.get("k1"));
        System.out.println();

        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        System.out.println();

        jedis.setex("k1", 1, "v1");
        System.out.println(jedis.get("k1"));
        System.out.println();

        TimeUnit.SECONDS.sleep(2);

        System.out.println(jedis.get("k1"));
        System.out.println();

        jedis.incr("k2");
        jedis.incrBy("k3", 2);
        System.out.println(jedis.get("k2"));
        System.out.println(jedis.get("k3"));
        System.out.println();

        List<String> values = jedis.mget("k2", "k3");
        for (int i = 0, len = values.size(); i < len; i++) {
            System.out.print(values.get(i) + "\t");
        }
        System.out.println();
        System.out.println();

        jedis.set("k4", "3.1415926");
        System.out.println(jedis.strlen("k4"));
    }
}