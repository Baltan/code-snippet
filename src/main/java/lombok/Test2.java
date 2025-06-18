package lombok;

import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.val;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-01 17:59
 */
public class Test2 {
    public static int value = 0;

    public static void main(String[] args) {
        /**
         * val定义常量
         */
        val CONSTANT = 100;
        System.out.println(CONSTANT);
        /**
         * 无法为最终变量CONSTANT分配值
         */
//        CONSTANT = 99;

        for (int i = 0; i < 100; i++) {
            new Thread(() -> add()).start();
        }
    }

    /**
     * 注解在方法上，将方法声明为同步的，并自动加锁，而锁对象是一个私有的属性$lock或$LOCK
     */
    @Synchronized
    @SneakyThrows
    public static void add() {
        value++;
        System.out.println(Thread.currentThread().getName() + " : " + value);
        TimeUnit.MILLISECONDS.sleep(100);
    }
}
