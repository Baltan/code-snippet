package concurrent;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Description: ConcurrentHashMap同步性能测试
 *
 * @author Baltan
 * @date 2019-05-22 17:07
 * @see SynchronizedMapPerformanceTest1
 * @see SynchronizedMapPerformanceTest2
 * @see SynchronizedMapPerformanceTest3
 * @see SynchronizedMapPerformanceTest4
 */
public class SynchronizedMapPerformanceTest5 {
    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        Map<String, Integer> map = new ConcurrentHashMap<>();
        int threadNum = 5;
        CountDownLatch latch = new CountDownLatch(threadNum);

        for (int i = 1; i <= threadNum; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 2000; j++) {
                    map.put(Thread.currentThread().getName() + "-" + j, j);
                }
                latch.countDown();
            }, "Thread" + i).start();
        }

        latch.await();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        System.out.println(map.size());
    }
}
