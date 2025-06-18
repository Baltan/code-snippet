package redis;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019/1/4 15:40
 */
public class ListTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        jedis.rpush("l1", "1", "2", "3", "4", "5");
        System.out.println(jedis.llen("l1"));
        System.out.println();

        List<String> l1 = jedis.lrange("l1", 0, -1);
        for (String s : l1) {
            System.out.print(s + "\t");
        }
        System.out.println();
        System.out.println();

        jedis.rpush("l2", "6", "7", "8", "9", "10");
        jedis.ltrim("l2", 0, 3);
        List<String> l2 = jedis.lrange("l2", 0, -1);
        for (String s : l2) {
            System.out.print(s + "\t");
        }
        System.out.println();
        System.out.println();

        jedis.linsert("l1", BinaryClient.LIST_POSITION.BEFORE, "2", "fuck");
        l1 = jedis.lrange("l1", 0, -1);
        for (String s : l1) {
            System.out.print(s + "\t");
        }
        System.out.println();
        System.out.println();

        System.out.println(jedis.lindex("l2", 3));
        System.out.println();

        jedis.rpoplpush("l1", "l2");
        l1 = jedis.lrange("l1", 0, -1);
        for (String s : l1) {
            System.out.print(s + "\t");
        }
        System.out.println();
        l2 = jedis.lrange("l2", 0, -1);
        for (String s : l2) {
            System.out.print(s + "\t");
        }
        System.out.println();
        System.out.println();

        jedis.lpush("l3", "1", "2", "3", "4", "5", "4", "4", "4");
        jedis.lrem("l3", 4, "4");
        List<String> l3 = jedis.lrange("l3", 0, -1);
        for (String s : l3) {
            System.out.print(s + "\t");
        }
    }
}