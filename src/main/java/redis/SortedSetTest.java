package redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019/1/4 19:28
 */
public class SortedSetTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        jedis.zadd("z1", 3, "zhangsan");
        jedis.zadd("z1", 4, "lisi");
        jedis.zadd("z1", 5, "wangwu");
        jedis.zadd("z1", 6, "zhaoliu");
        System.out.println(jedis.zcard("z1"));
        System.out.println();

        System.out.println(jedis.zcount("z1", 4, 5));
        System.out.println();

        Set<String> set = jedis.zrange("z1", 0, -1);
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        System.out.println();

        set = jedis.zrevrangeByScore("z1", 5, 3);
//        set = jedis.zrangeByScore("z1", 3, 5);
        it = set.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
        System.out.println();

        System.out.println(jedis.zrank("z1", "wangwu"));
        System.out.println();

        System.out.println(jedis.zscore("z1", "zhaoliu"));
        System.out.println();

        jedis.zadd("z2", 6, "zhangsan");
        jedis.zadd("z2", 5, "lisi");
        jedis.zadd("z2", 4, "wangwu");
        jedis.zadd("z2", 3, "zhaoliu");
        jedis.zadd("z2", 2, "tianqi");
        jedis.zadd("z2", 1, "zhuba");
        jedis.zunionstore("z3", "z1", "z2");
        set = jedis.zrange("z3", 0, -1);
        it = set.iterator();
        while (it.hasNext()) {
            String field = it.next();
            System.out.println(field + ":" + jedis.zscore("z3", field));
        }
        System.out.println();

        jedis.zinterstore("z3", "z1", "z2");
        set = jedis.zrange("z3", 0, -1);
        it = set.iterator();
        while (it.hasNext()) {
            String field = it.next();
            System.out.println(field + ":" + jedis.zscore("z3", field));
        }
    }
}
