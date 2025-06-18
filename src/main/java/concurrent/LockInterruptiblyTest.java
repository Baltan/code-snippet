package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/9/30 10:45
 */
public class LockInterruptiblyTest {

    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            LOCK.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            boolean getLock = false;

            try {
                LOCK.lockInterruptibly();
                getLock = true;
                TimeUnit.SECONDS.sleep(5);
                System.out.println("呦西……");
            } catch (InterruptedException e) {
                System.out.println("不等了……");
            } finally {
                if (getLock) {
                    LOCK.unlock();
                }
            }
        });

        t1.start();
        t2.start();

        t2.interrupt(); // 中断t2线程，t2线程不再等待获取锁
    }
}
