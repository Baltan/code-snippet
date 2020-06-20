package concurrent_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/9/30 11:10
 */
public class TryLockTest {

    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(() -> {
            LOCK.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }).start();

        new Thread(() -> {
            boolean flag = false;
            try {
                flag = LOCK.tryLock(5, TimeUnit.SECONDS); // 5秒钟后如果还不能获得锁，则不再获取，返回是否得到锁的布尔值
                if (flag) {
                    System.out.println("呦西……");
                } else {
                    System.out.println("不等了……");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (flag) {
                    LOCK.unlock();
                }
            }
        }).start();
    }
}
