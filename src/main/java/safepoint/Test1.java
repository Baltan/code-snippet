package safepoint;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/9/11 20:45
 * @see <a href="https://juejin.cn/post/7139741080597037063">没有二十年功力，写不出Thread.sleep(0)这一行“看似无用”的代码！</a>
 * @see <a href="https://mp.weixin.qq.com/s/KDUccdLALWdjNBrFjVR74Q">真是绝了！这段被JVM动了手脚的代码！</a>
 */
public class Test1 {
    public static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 100000000; i++) {
                num.getAndAdd(1);
            }
            System.out.println(Thread.currentThread().getName() + "执行结束!");
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("num = " + num);
    }
}
