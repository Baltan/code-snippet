package concurrent_test;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Description: 生产一个，消费一个的生产消费模型，用"synchronized"实现同步，用"wait"和"notifyAll"实现等待和唤醒线程
 *
 * @author Baltan
 * @date 2018/6/11 09:33
 */
public class ProducerAndConsumerDemo1 {
    public static void main(String[] args) {
        Bread1 bread1 = new Bread1();
        Consumer1 consumer = new Consumer1(bread1);
        Producer1 producer = new Producer1(bread1);

        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
    }
}

class Bread1 {
    private int count = 0;

    private boolean hasBread = false;

    public synchronized void produce() {
        if (!hasBread) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            System.out.println("    " + Thread.currentThread().getName() + "生产了面包" + count);
            hasBread = true;
            this.notifyAll();
        } else {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consume() {
        if (hasBread) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "消费了面包" + count);
            hasBread = false;
            this.notifyAll();
        } else {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer1 implements Runnable {
    private Bread1 bread1;

    public Producer1(Bread1 bread1) {
        this.bread1 = bread1;
    }

    @Override
    public void run() {
        while (true) {
            bread1.produce();
        }
    }
}

class Consumer1 implements Runnable {
    private Bread1 bread1;

    public Consumer1(Bread1 bread1) {
        this.bread1 = bread1;
    }

    @Override
    public void run() {
        while (true) {
            bread1.consume();
        }
    }
}