package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2025/8/6 22:51
 */
public class ThreadLocalTest1 {
    private static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            int j = i;
            new Thread(() -> {
                THREAD_LOCAL.set(j);
                try {
                    TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("线程-%d持有的本地线程变量为%d%n", j, THREAD_LOCAL.get());
            }).start();
        }
    }
}
