package guava_test;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Description: Reference: https://zhuanlan.zhihu.com/p/76612241
 *
 * @author Baltan
 * @date 2019-08-06 15:56
 */
public class RateLimiterTest {
    public static void main(String[] args) {
        /**
         * 每秒钟令牌桶中产生1个令牌
         */
        RateLimiter rateLimiter = RateLimiter.create(1);
        long timeout = (long) 0.9;


        for (int i = 0; i < 10; i++) {
            /**
             * 该方法会阻塞线程，直到令牌桶中能取到令牌为止才继续向下执行
             */
//            double waitTime = rateLimiter.acquire();
//            System.out.println("任务" + i + "等待时间：" + waitTime);
            /**
             * 设定一个超时的时间，如果在指定的时间内预估(注意是预估并不会真实的等待)，
             * 如果能拿到令牌就返回true，如果拿不到就返回false
             */
            boolean isValid = rateLimiter.tryAcquire(timeout, TimeUnit.SECONDS);
            System.out.println("任务" + i + "执行是否有效：" + isValid);
            if (isValid) {
                System.out.println("任务" + i + "已执行");
            }
        }
    }
}
