package concurrent;

import java.util.concurrent.TimeUnit;

public class AtomicityTest {
    public static int count = 0;

    public static synchronized void incrementAndOutput() {
        count++;
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 没有按序输出，因为++操作和System.out.println()方法不是原子性的，需要在inc()方法上加上"synchronized"关键字
        System.out.println("count=" + count);
    }

    public static void main(String[] args) {
        // 同时启动1000个线程，去进行i++计算，看看实际结果
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> incrementAndOutput()).start();
        }
    }
}
