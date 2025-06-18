package concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/11 17:40
 */
public class NewFixedThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int j = 0; j < 100; j++) {
            Future future = service.submit(() -> {
                int num = new Random().nextInt(20) + 1;
                int sum = 0;
                for (int i = 1; i <= num; i++) {
                    sum += i;
                }
                System.out.println(Thread.currentThread().getName() + "的sum的值为：" + sum);
                return sum;
            });
            System.out.println("计算结果：" + future.get());
        }
        service.shutdown();
    }
}
