package concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 多生产多消费模型，用ReentrantLock实现同步，用condition实现等待和唤醒生产者或消费者线程
 *
 * @author Baltan
 * @date 2018/12/28 16:40
 */
public class ProducerAndConsumerDemo4 {
    public static void main(String[] args) {
        Bread4 bread = new Bread4();

        new Thread(new Consumer4(bread), "Consumer-1").start();
        new Thread(new Producer4(bread), "Producer-1").start();
        new Thread(new Consumer4(bread), "Consumer-2").start();
        new Thread(new Producer4(bread), "Producer-2").start();
        new Thread(new Consumer4(bread), "Consumer-3").start();
        new Thread(new Producer4(bread), "Producer-3").start();
    }
}

class Bread4 {
    private int count = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private final Random rand = new Random();

    public void produce() {
        try {
            lock.lock();
            while (count >= 10) {
                condition.await();
            }
            TimeUnit.MILLISECONDS.sleep(100);
            int produceNum = rand.nextInt(3) + 1;

            if (produceNum + count > 10) {
                produceNum = 10 - count;
                count = 10;
            } else {
                count += produceNum;
            }

            System.out.println(
                    Thread.currentThread().getName() + "生产了" + produceNum + "个面包，还剩" + count + "个面包");
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        try {
            lock.lock();
            while (count == 0) {
                condition.await();
            }
            TimeUnit.NANOSECONDS.sleep(10);
            int consumeNum = rand.nextInt(3) + 1;

            if (count - consumeNum < 0) {
                consumeNum = count;
                count = 0;
            } else {
                count -= consumeNum;
            }

            System.out.println(
                    "     " + Thread.currentThread().getName() + "消费了" + consumeNum + "个面包，还剩" + count +
                            "个面包");
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class Producer4 implements Runnable {
    private Bread4 bread;

    public Producer4(Bread4 bread) {
        this.bread = bread;
    }

    @Override
    public void run() {
        while (true) {
            bread.produce();
            try {
                TimeUnit.NANOSECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer4 implements Runnable {
    private Bread4 bread;

    public Consumer4(Bread4 bread) {
        this.bread = bread;
    }

    @Override
    public void run() {
        while (true) {
            bread.consume();
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
