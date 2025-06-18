package flash_sale;

import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: Reference：https://www.cnblogs.com/longtaosoft/p/6627568.html
 *
 * @author Baltan
 * @date 2019-08-02 15:37
 */
public class FlashSaleTest {
    public static void main(String[] args) {
        final String productId = "iPhone XS MAX";
        ExecutorService executor = Executors.newFixedThreadPool(20);
        final Jedis jedis = new Jedis("localhost", 6379);
        /**
         * 可以抢购的商品数量：100
         */
        jedis.set(productId, "100");
        jedis.close();
        /**
         * 10000人同时抢购
         */
        for (int i = 0; i < 10000; i++) {
            executor.execute(new FlashSaleThread(productId, "USER-" + i));
        }
        executor.shutdown();
    }
}
