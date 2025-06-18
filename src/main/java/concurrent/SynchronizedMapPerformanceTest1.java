package concurrent;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Description: Collections.synchronizedMap()同步性能测试
 *
 * @author Baltan
 * @date 2019-05-22 16:18
 * @see SynchronizedMapPerformanceTest2
 * @see SynchronizedMapPerformanceTest3
 * @see SynchronizedMapPerformanceTest4
 * @see SynchronizedMapPerformanceTest5
 */
public class SynchronizedMapPerformanceTest1 {
    private static class Print implements Runnable {
        private Map<String, Integer> map;
        private CountDownLatch latch;

        public Print(Map<String, Integer> map, CountDownLatch latch) {
            this.map = map;
            this.latch = latch;
        }

        @Override
        public void run() {
            for (int j = 1; j <= 2000; j++) {
                map.put(Thread.currentThread().getName() + "-" + j, j);
            }
            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> syncMap = Collections.synchronizedMap(map);
        int threadNum = 5;
        CountDownLatch latch = new CountDownLatch(threadNum);

        for (int i = 1; i <= threadNum; i++) {
            new Thread(new Print(syncMap, latch), "Thread" + i).start();
        }

        latch.await();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        System.out.println(syncMap.size());
    }
}
