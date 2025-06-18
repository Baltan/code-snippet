package concurrent_test;

import java.util.concurrent.locks.LockSupport;

/**
 * Description:
 *
 * @author Baltan
 * @date 2023/1/17 19:45
 */
public class LockSupportTest {
    private static volatile boolean t1Print = false;
    private static Thread t1;
    private static Thread t2;

    /**
     * 线程t1和t2交替打印数字1和2
     *
     * @param args
     */
    public static void main(String[] args) {
        t1 = new Thread(() -> {
            while (true) {
                while (!t1Print) {
                    LockSupport.park();
                }
                System.out.print(1);
                t1Print = !t1Print;
                LockSupport.unpark(t2);
            }
        });

        t2 = new Thread(() -> {
            while (true) {
                while (t1Print) {
                    LockSupport.park();
                }
                System.out.print(2);
                t1Print = !t1Print;
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        t2.start();
    }
}
