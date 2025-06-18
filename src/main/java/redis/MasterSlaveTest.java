package redis;

import redis.clients.jedis.Jedis;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019/1/7 15:38
 */
public class MasterSlaveTest {
    public static void main(String[] args) {
        Jedis masterJedis = new Jedis("127.0.0.1", 6379);
        Jedis slaveJedis = new Jedis("127.0.0.1", 6380);

//        slaveJedis.slaveofNoOne();
//        slaveJedis.flushDB();
//        masterJedis.slaveofNoOne();
//        masterJedis.flushDB();

        slaveJedis.slaveof("127.0.0.1", 6379);

        masterJedis.set("k1", "v1");
        System.out.println(slaveJedis.get("k1"));
    }
}
