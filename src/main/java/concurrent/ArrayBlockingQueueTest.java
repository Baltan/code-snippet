package concurrent_test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/10/5 16:33
 */
public class ArrayBlockingQueueTest {
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            queue.put("a" + i);
        }

        queue.put("666"); // 等待，程序阻塞
//        queue.add("666"); // 抛出IllegalStateException:Queue full异常
//        System.out.println(queue.offer("666")); // 不抛异常，但是不会加入元素，返回元素是否添加成功的布尔值
//        System.out.println(queue.offer("666", 1, TimeUnit.SECONDS)); // 若1秒钟之后元素添加不进去，就不再添加元素

        System.out.println(queue);
    }
}
