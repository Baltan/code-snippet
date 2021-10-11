package concurrent_test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * Description: 测试不同线程修改数组不同位置的元素，是否会存在并发问题
 *
 * @author Baltan
 * @date 2021/10/11 19:59
 */
public class ConcurrentArrayTest {
    public static void main(String[] args) {
        final int THREAD_COUNT = 4;

        for (int i = 0; i < 500; i++) {
            int[] arr = new int[THREAD_COUNT];
            CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

            for (int j = 0; j < THREAD_COUNT; j++) {
                final int K = j;
                new Thread(() -> {
                    arr[K] = K;
                    latch.countDown();
                }).start();
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
