package concurrent_test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/20 16:08
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        Demo d = new Demo();
        for (int i = 0; i < 10; i++) {
            new Thread(d).start();
        }
    }
}

class Demo implements Runnable {
    private AtomicInteger num = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
//        System.out.println(num.getAndIncrement());
        synchronized (this) {
            System.out.println(num.getAndIncrement());
        }
    }
}
