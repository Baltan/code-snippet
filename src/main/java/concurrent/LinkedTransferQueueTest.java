package concurrent;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/10/5 17:31
 */
public class LinkedTransferQueueTest {
    public static void main(String[] args) {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        // 先启动消费者线程，transfer()正常执行
        new Thread(() -> {
            try {
                while (true) {
                    System.out.println(queue.take());
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            for (int i = 0; i < 100; i++) {
                /**
                 * 使用transfer()方法时，元素被直接从生产者移交给消费者，所以队列长度一直保持为0，类似SynchronousQueue
                 * 先启动消费者线程时，元素直接交给消费者线程消费
                 * 后启动消费者线程时，transfer()方法阻塞
                 */
                queue.transfer("" + i); // 当前没有消费者时，这个方法会一直阻塞
                /**
                 * 使用put()方法时，元素可以被保存在队列中，队列的长度会一直增长
                 * 不论先启动还是后启动消费者线程，put()方法都不会阻塞，元素都会被添加进队列中
                 * offer()和add()方法执行过程和put()一样，但是会返回true
                 */
//                queue.put("" + i);
//                queue.offer("" + i);
//                queue.offer("" + i, 1, TimeUnit.SECONDS);
//                queue.add("" + i);
                System.out.println("queue.size: " + queue.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 后启动消费者线程，transfer()方法阻塞
//        new Thread(() -> {
//            try {
//                while (true) {
//                    System.out.println(queue.take());
//                    TimeUnit.MILLISECONDS.sleep(500);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
