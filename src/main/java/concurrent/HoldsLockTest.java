package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-18 18:58
 */
public class HoldsLockTest {
    public static void main(String[] args) {
        Object o = new Object();

        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "持有对象锁-----" + Thread.holdsLock(o));
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程-1").start();

        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "持有对象锁-----" + Thread.holdsLock(o));
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程-2").start();

        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "持有对象锁-----" + Thread.holdsLock(o));
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程-3").start();
    }
}
