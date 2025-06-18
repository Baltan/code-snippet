package concurrent_test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/10/9 12:01
 */
public class SynchronousQueueTest {
    public static void main(String[] args) throws InterruptedException {
// 容量为0，必须立刻消费，使用add()方法抛出抛出IllegalStateException:Queue full异常，使用put()方法阻塞等待消费者消费
        BlockingQueue<String> queue = new SynchronousQueue<>();

        // 先启动消费者线程
//        new Thread(() -> {
//            try {
//                System.out.println(queue.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        /**
         * 当前没有消费者时，这个方法会一直阻塞
         */
        queue.put("666666");
        /**
         * 不论当前是否有消费者，抛出IllegalStateException:Queue full异常
         */
//        queue.add("666666");
        /**
         * 当前有消费者时，元素直接交给消费者消费，并返回true
         * 没有消费者时返回false，并且后启动的消费者线程的take()方法会阻塞
         */
//        System.out.println(queue.offer("666666"));
//        queue.offer("666666", 1, TimeUnit.SECONDS);

        // 后启动消费者线程
        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
