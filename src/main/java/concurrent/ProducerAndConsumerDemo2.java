package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 生产一个，消费一个的生产消费模型，用ReentrantLock实现同步，用"await"和"signalAll"实现等待和唤醒线程
 *
 * @author Baltan
 * @date 2018/6/11 23:12
 */
public class ProducerAndConsumerDemo2 {
    public static void main(String[] args) {
        Bread2 bread = new Bread2();
        Consumer2 consumer = new Consumer2(bread);
        Producer2 producer = new Producer2(bread);

        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
    }
}

class Bread2 {
    private int count = 0;

    private boolean hasBread = false;

    private Lock lock = new ReentrantLock();

    private Condition con = lock.newCondition();

    public void produce() {
        try {
            lock.lock();
            if (!hasBread) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.println(Thread.currentThread().getName() + "生产了面包" + count);
                hasBread = true;
                con.signalAll();
            } else {
                try {
                    con.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        try {
            lock.lock();
            if (hasBread) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("    " + Thread.currentThread().getName() + "消费了面包" + count);
                hasBread = false;
                con.signalAll();
            } else {
                try {
                    con.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }
}

class Producer2 implements Runnable {
    private Bread2 bread;

    public Producer2(Bread2 bread) {
        this.bread = bread;
    }

    @Override
    public void run() {
        while (true) {
            bread.produce();
        }
    }
}

class Consumer2 implements Runnable {
    private Bread2 bread;

    public Consumer2(Bread2 bread) {
        this.bread = bread;
    }

    @Override
    public void run() {
        while (true) {
            bread.consume();
        }
    }
}
