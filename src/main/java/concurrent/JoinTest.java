package concurrent;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/22 23:13
 */
public class JoinTest {
    public static void main(String[] args) {
        long start = System.nanoTime();


        Thread t = new Thread(new Demo2());
        t.start();

        try {
            Thread.sleep(20);
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.nanoTime();

        long time = end - start;

        System.out.println("总耗时：" + time + "纳秒");
    }
}

class Demo1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}

class Demo2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Demo1()).start();
        }
    }
}
