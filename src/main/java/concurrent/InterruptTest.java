package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/12/27 10:50
 */
public class InterruptTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("thread-1睡醒了……");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-1");
        thread1.start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("thread-1中断了-----" + thread1.isInterrupted());
                System.out.println("thread-2中断了-----" + Thread.currentThread().isInterrupted());
                /**
                 * 中断Thread-1
                 */
                thread1.interrupt();
                System.out.println("thread-1中断了-----" + thread1.isInterrupted());
                /**
                 * 中断当前线程，即Thread-2
                 */
                Thread.currentThread().interrupt();
                System.out.println("thread-2中断了-----" + Thread.currentThread().isInterrupted());
                /**
                 * interrupted()测试当前线程是否已经是中断状态，执行后会将状态标志清除
                 */
                System.out.println("thread-2中断了-----" + Thread.interrupted());
                System.out.println("thread-2中断了-----" + Thread.interrupted());
                System.out.println("thread-2中断了-----" + Thread.currentThread().isInterrupted());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-2").start();
    }
}
