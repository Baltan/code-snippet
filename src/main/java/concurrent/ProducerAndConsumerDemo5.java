package concurrent_test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description: 多生产多消费模型，用BlockingQueue实现
 *
 * @author Baltan
 * @date 2019-03-18 18:37
 */
public class ProducerAndConsumerDemo5 {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(20);
        Random rand = new Random();

        new Thread(new Producer5(queue, rand), "生产者-1").start();
        new Thread(new Producer5(queue, rand), "生产者-2").start();
        new Thread(new Producer5(queue, rand), "生产者-3").start();
        new Thread(new Producer5(queue, rand), "生产者-4").start();
        new Thread(new Producer5(queue, rand), "生产者-5").start();
        new Thread(new Comsumer5(queue, rand), "消费者-1").start();
        new Thread(new Comsumer5(queue, rand), "消费者-2").start();
        new Thread(new Comsumer5(queue, rand), "消费者-3").start();
        new Thread(new Comsumer5(queue, rand), "消费者-4").start();
        new Thread(new Comsumer5(queue, rand), "消费者-5").start();
    }
}

class Producer5 implements Runnable {
    private BlockingQueue<Integer> queue;
    private Random rand;

    public Producer5(BlockingQueue<Integer> queue, Random rand) {
        this.queue = queue;
        this.rand = rand;
    }

    private void produce() {
        try {
            int value = rand.nextInt(10) * 500;
            queue.put(value);
            System.out.println(Thread.currentThread().getName() + "生产----------" + value);
            TimeUnit.MILLISECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }
}

class Comsumer5 implements Runnable {
    private BlockingQueue<Integer> queue;
    private Random rand;

    public Comsumer5(BlockingQueue<Integer> queue, Random rand) {
        this.queue = queue;
        this.rand = rand;
    }

    private void consume() {
        try {
            int value = queue.take();
            System.out.println("    " + Thread.currentThread().getName() + "消费----------" + value);
            TimeUnit.MILLISECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            consume();
        }
    }
}