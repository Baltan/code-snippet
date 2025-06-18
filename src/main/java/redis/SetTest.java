package redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019/1/4 16:02
 */
public class SetTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        jedis.sadd("s1", "1", "2", "2", "2", "3", "4", "4", "5", "5", "6", "6");
        System.out.println(jedis.scard("s1"));
        System.out.println();

        Set<String> s1 = jedis.smembers("s1");
        Iterator<String> it = s1.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        System.out.println();

        System.out.println(jedis.sismember("s1", "6"));
        System.out.println(jedis.sismember("s1", "7"));
        System.out.println();

        jedis.srem("s1", "2", "4", "6");
        s1 = jedis.smembers("s1");
        it = s1.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        System.out.println();

        for (int i = 0; i < 5; i++) {
            System.out.print(jedis.srandmember("s1") + "\t");
        }
        System.out.println();
        System.out.println();

        jedis.sadd("s2", "2", "3", "4", "5", "6");
        System.out.print("s1-s2:\t");
        Set<String> diff1 = jedis.sdiff("s1", "s2");
        it = diff1.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        System.out.print("s2-s1:\t");
        Set<String> diff2 = jedis.sdiff("s2", "s1");
        it = diff2.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        System.out.print("s1∩s2:\t");
        Set<String> inter = jedis.sinter("s1", "s2");
        it = inter.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        System.out.print("s1∪s2:\t");
        Set<String> union = jedis.sunion("s1", "s2");
        it = union.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        System.out.println();

        jedis.sadd("s3", "a", "b", "c");
        jedis.sadd("s4", "d", "e", "f");
        jedis.smove("s3", "s4", "a");
        Set<String> s3 = jedis.smembers("s3");
        it = s3.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        Set<String> s4 = jedis.smembers("s4");
        it = s4.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
    }
}
