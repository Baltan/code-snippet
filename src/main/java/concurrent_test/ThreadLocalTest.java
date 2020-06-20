package concurrent_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/12/17 14:35
 */
public class ThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();

        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        ThreadLocal<String> mainThreadLocal = new ThreadLocal<>();
        mainThreadLocal.set("main threadLocal");

        ThreadLocal<String> mainInheritableThreadLocal = new InheritableThreadLocal<>();
        mainInheritableThreadLocal.set("main mainInheritableThreadLocal");

        for (int i = 0; i < 5; i++) {
            final int j = i;
            new Thread(() -> {
                threadLocal.set(j);
                int num = threadLocal.get();

                lock.lock();

                try {
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    System.out.println("main threadLocal: " + mainThreadLocal.get());
                    System.out
                            .println("main mainInheritableThreadLocal: " + mainInheritableThreadLocal.get());
                    System.out.println("-----------------------------------------------------------");
                } finally {
                    lock.unlock();
                }
            }, "Thread" + i).start();
        }

        TimeUnit.MILLISECONDS.sleep(100);

        System.out.println(Thread.currentThread().getName());
        System.out.println("main threadLocal: " + mainThreadLocal.get());
        System.out.println("main mainInheritableThreadLocal: " + mainInheritableThreadLocal.get());
    }
}
