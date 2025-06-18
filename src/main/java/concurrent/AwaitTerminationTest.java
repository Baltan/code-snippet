package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019/1/9 19:36
 */
public class AwaitTerminationTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        service.execute(() -> {
            System.out.println("线程1启动");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.execute(() -> {
            System.out.println("线程2启动");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(1);
        service.shutdown();
        System.out.println("线程池关闭");

        while (!service.awaitTermination(10, TimeUnit.MILLISECONDS)) {
            System.out.println("线程还在执行");
        }

        System.out.println("主线程结束");
    }
}
