package concurrent_test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/10/5 16:22
 */
public class LinkedBlockingQueueTest {

    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    private static Random rand = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.put("a" + i); // 如果满了，就会等待
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "producer1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " takes -- " + queue.take());
                        // 如果空了，就会等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "consumer" + i).start();
        }
    }
}
