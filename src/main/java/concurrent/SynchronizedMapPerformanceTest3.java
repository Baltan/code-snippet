package concurrent_test;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: ReentrantLock+HashMap同步性能测试
 *
 * @author Baltan
 * @date 2019-05-22 16:49
 * @see SynchronizedMapPerformanceTest1
 * @see SynchronizedMapPerformanceTest2
 * @see SynchronizedMapPerformanceTest4
 * @see SynchronizedMapPerformanceTest5
 */
public class SynchronizedMapPerformanceTest3 {
    private static class Print implements Runnable {
        private Map<String, Integer> map;
        private CountDownLatch latch;
        private Lock lock;

        public Print(Map<String, Integer> map, CountDownLatch latch, Lock lock) {
            this.map = map;
            this.latch = latch;
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int j = 1; j <= 2000; j++) {
                lock.lock();
                try {
                    map.put(Thread.currentThread().getName() + "-" + j, j);
                } finally {
                    lock.unlock();
                }
            }
            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        Map<String, Integer> map = new HashMap<>();
        int threadNum = 5;
        CountDownLatch latch = new CountDownLatch(threadNum);
        Lock lock = new ReentrantLock();

        for (int i = 1; i <= threadNum; i++) {
            new Thread(new Print(map, latch, lock), "Thread" + i).start();
        }

        latch.await();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        System.out.println(map.size());
    }
}
