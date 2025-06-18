package concurrent_test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Description: 所有调用CyclicBarrier的await()方法的线程会在所有await()方法都被执行后再同时向下执行
 *
 * @author Baltan
 * @date 2019/1/9 19:19
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " starts");

            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " ends");
        }, "thread-1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " starts");

            try {
                TimeUnit.SECONDS.sleep(3);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " ends");
        }, "thread-2").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " starts");

            try {
                TimeUnit.SECONDS.sleep(5);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " ends");
        }, "thread-3").start();

        System.out.println("FUCK ME!");
    }
}
