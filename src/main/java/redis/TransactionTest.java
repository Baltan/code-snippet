package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019/1/7 15:00
 */
public class TransactionTest {

    private Integer balance = 1000;
    private Integer debt = 0;
    private Integer consumption = 100;

    Jedis jedis;

    public void init() {
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        jedis.set("balance", String.valueOf(balance));
        jedis.set("debt", String.valueOf(debt));
    }

    public boolean consume() {
        jedis.watch("balance");
//        jedis.set("balance", "5"); // 模拟balance被其他线程修改

        balance = Integer.valueOf(jedis.get("balance"));

        if (balance < consumption) {
            jedis.unwatch();
            System.out.println("Balance has been modified.");
            return false;
        } else {
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance", consumption);
            transaction.incrBy("debt", consumption);
            transaction.exec();

            balance = Integer.valueOf(jedis.get("balance"));
            debt = Integer.valueOf(jedis.get("debt"));
            System.out.println("balance: " + balance);
            System.out.println("debt: " + debt);
            return true;
        }
    }

    public static void main(String[] args) {
        TransactionTest tt = new TransactionTest();
        tt.init();
        System.out.println(tt.consume());
    }
}
