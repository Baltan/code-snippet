package concurrent;

import java.util.concurrent.Semaphore;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/12/25 15:05
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        final int N = 20;
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < N; i++)
            new Worker(i, semaphore).start();
    }
}

class Worker extends Thread {
    private int num;
    private Semaphore semaphore;

    public Worker(int num, Semaphore semaphore) {
        this.num = num;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();

            System.out.println("工人" + this.num + "占用一个机器在生产...");
            Thread.sleep(2000);
            System.out.println("工人" + this.num + "释放出机器");

            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}