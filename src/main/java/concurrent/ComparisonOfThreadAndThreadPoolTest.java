package concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/18 17:03
 */
public class ComparisonOfThreadAndThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        ExecutorService service = Executors.newFixedThreadPool(5);

        long start1 = System.nanoTime();

        for (int i = 0; i < 50000; i++) {
            final int j = i;
            Thread t = new Thread(() -> list1.add(j));
            t.start();
            t.join(); // 确保50000个线程都执行完，再结束计时
        }

        long end1 = System.nanoTime();

        System.out.println("用时：" + (end1 - start1));
        System.out.println("list1大小：" + list1.size());

        System.out.println("__________________________________________");

        long start2 = System.nanoTime();

        for (int i = 0; i < 50000; i++) {
            final int j = i;
            service.execute(() -> list2.add(j));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);

        long end2 = System.nanoTime();

        System.out.println("用时：" + (end2 - start2));
        System.out.println("list2大小：" + list2.size());
    }
}
