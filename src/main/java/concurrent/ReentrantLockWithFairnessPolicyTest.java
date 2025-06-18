package concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/9/30 10:06
 */
public class ReentrantLockWithFairnessPolicyTest {

    //    private static final Lock LOCK = new ReentrantLock();
    private static final Lock LOCK = new ReentrantLock(true); // 公平锁，哪个线程等候的时间长，哪个线程就能得到锁

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                LOCK.lock();
                try {
                    System.out.println("线程1：" + i);
                } finally {
                    LOCK.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                LOCK.lock();
                try {
                    System.out.println("线程2：" + i);
                } finally {
                    LOCK.unlock();
                }
            }
        }).start();
    }
}
