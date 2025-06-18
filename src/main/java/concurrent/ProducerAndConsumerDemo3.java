package concurrent_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 生产一个，消费一个的生产消费模型，用ReentrantLock实现同步，用不同的condition实现指定等待和唤醒生产者或消费者线程
 *
 * @author Baltan
 * @date 2018/6/13 21:07
 */
public class ProducerAndConsumerDemo3 {
    public static void main(String[] args) {
        Bread3 bread = new Bread3();
        Consumer3 consumer = new Consumer3(bread);
        Producer3 producer = new Producer3(bread);

        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
    }
}

class Bread3 {
    private int count = 0;

    private boolean hasBread = false;

    private Lock lock = new ReentrantLock();

    private Condition producerCon = lock.newCondition();
    private Condition consumerCon = lock.newCondition();

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
                consumerCon.signal();
            } else {
                try {
                    producerCon.await();
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
                producerCon.signal();
            } else {
                try {
                    consumerCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }
}

class Producer3 implements Runnable {
    private Bread3 bread;

    public Producer3(Bread3 bread) {
        this.bread = bread;
    }

    @Override
    public void run() {
        while (true) {
            bread.produce();
        }
    }
}

class Consumer3 implements Runnable {
    private Bread3 bread;

    public Consumer3(Bread3 bread) {
        this.bread = bread;
    }

    @Override
    public void run() {
        while (true) {
            bread.consume();
        }
    }
}

