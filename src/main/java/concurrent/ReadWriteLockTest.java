package concurrent;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/27 22:30
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        for (int i = 0; i < 3; i++) {
            System.out.println("创建线程：writeThread" + i);
            new Thread(() -> demo.write(), "writeThread" + i).start();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println("创建线程：readThread" + i);
            new Thread(() -> demo.read(), "readThread" + i).start();
        }
    }
}

class ReadWriteLockDemo {
    private int num;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        Lock readLock = lock.readLock();

        for (int i = 0; i < 3; i++) {
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "-----------" + num);
                sleep(200, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + "-----------num读取完毕");
            } finally {
                readLock.unlock();
            }
        }
    }

    public void write() {
        Lock writeLock = lock.writeLock();

        for (int i = 0; i < 3; i++) {
            writeLock.lock();
            try {
                num = new Random().nextInt(100);
                System.out.println(Thread.currentThread().getName() + "-----------" + num);
                sleep(500, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + "-----------num修改完毕");
                sleep(1000, TimeUnit.MILLISECONDS);
            } finally {
                writeLock.unlock();
                sleep(500, TimeUnit.MILLISECONDS);
            }
        }
    }

    public void sleep(int time, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

