package flash_sale_test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Description: Reference：https://www.cnblogs.com/longtaosoft/p/6627568.html
 *
 * @author Baltan
 * @date 2019-08-02 15:36
 */
public class FlashSaleThread implements Runnable {
    private String productId;
    private String userId;
    private Jedis jedis = new Jedis("localhost", 6379);

    public FlashSaleThread(String productId, String userId) {
        this.productId = productId;
        this.userId = userId;
    }

    @Override
    public void run() {
        try {
            /**
             * 在Redis的事务中，WATCH命令可用于提供CAS(check-and-set)功能。
             * 假设我们通过WATCH命令在事务执行之前监控了多个Keys，倘若在WATCH之后有任何Key的值发生了变化，
             * EXEC命令执行的事务都将被放弃，同时返回Null multi-bulk应答以通知调用者事务执行失败。
             */
            jedis.watch(productId);
            int count = Integer.valueOf(jedis.get(productId));

            if (count <= 100 && count >= 1) {
                /**
                 * 开启事务
                 */
                Transaction tx = jedis.multi();
                /**
                 * 将商品数量减1
                 */
                tx.incrBy(productId, -1);
                /**
                 * 提交事务，如果此时productId的值被改动了，则返回null
                 */
                List<Object> list = tx.exec();

                if (list == null || list.size() == 0) {
                    String info = "用户：" + userId + "，抢购失败";
                    System.out.println(info);
                    /**
                     * 此处可以添加抢购失败的业务逻辑
                     */
                } else {
                    for (Object success : list) {
                        String info = "用户：" + userId + "，抢购成功，当前抢购成功人数:"
                                + (100 - Integer.valueOf(success.toString()));
                        System.out.println(info);
                        /**
                         * 此处可以添加抢购成功的业务逻辑
                         */
                    }
                }
            } else {
                String info = "用户：" + userId + "，商品被抢购完毕，抢购失败";
                System.out.println(info);
                /**
                 * 此处可以添加抢购失败的业务逻辑
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}