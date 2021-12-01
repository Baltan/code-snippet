package concurrent_test;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2021/12/1 11:26
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        int[] container = new int[3];

        CompletableFuture future1 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            container[0] = 1;
        });

        CompletableFuture future2 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            container[1] = 2;
        });

        CompletableFuture future3 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            container[2] = 3;
        });

        System.out.println(Arrays.toString(container));
        CompletableFuture.allOf(future1, future2, future3).join();
        System.out.println(Arrays.toString(container));
    }
}
