package concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/11 22:37
 */
public class NewScheduledThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        for (int j = 0; j < 20; j++) {
            Future future = service.schedule(() -> {
                int num = new Random().nextInt(20) + 1;
                int sum = 0;
                for (int i = 1; i <= num; i++) {
                    sum += i;
                }
                System.out.println(Thread.currentThread().getName() + "的sum的值为：" + sum);
                return sum;
            }, 1, TimeUnit.SECONDS);
            System.out.println("计算结果：" + future.get());
        }
        service.shutdown();
    }
}
