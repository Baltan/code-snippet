package concurrent;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.TimeUnit;

/**
 * Description: 对象线程安全测试
 *
 * @author Baltan
 * @date 2024/2/29 21:59
 */
public class ThreadSafetyTest {
    public static void main(String[] args) {
        demo1();
        System.out.println("-------------------------------------------");
        demo2();
    }

    /**
     * 线程不安全，线程间共享了Holder，num被错误修改
     */
    private static void demo1() {
        Holder holder = new Holder().setNum(-1);

        for (int i = 0; i < 10000; i++) {
            holder.setNum(i);
            new Thread(() -> {
                System.out.println("第一次打印：" + Thread.currentThread().getName() + "  ===>  " + holder);
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第二次打印：" + Thread.currentThread().getName() + "  ===>  " + holder);
            }, "Thread---" + i).start();
        }
    }

    /**
     * 线程安全，每个线程拥有自己的Holder副本
     */
    private static void demo2() {
        Holder holder = new Holder().setNum(-1);

        for (int i = 0; i < 10000; i++) {
            Holder ownHolder = new Holder();
            BeanUtil.copyProperties(holder, ownHolder);
            ownHolder.setNum(i);
            new Thread(() -> {
                System.out.println("第一次打印：" + Thread.currentThread().getName() + "  ===>  " + ownHolder);
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第二次打印：" + Thread.currentThread().getName() + "  ===>  " + ownHolder);
            }, "Thread---" + i).start();
        }
    }

    @Data
    @Accessors(chain = true)
    public static class Holder {
        private int num;
    }
}
