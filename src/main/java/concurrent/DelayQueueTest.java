package concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/10/5 16:50
 */
public class DelayQueueTest {

    private static BlockingQueue<MyTask> tasks = new DelayQueue<>();

    private static class MyTask implements Delayed {

        private long runningTime;

        public MyTask(long runningTime) {
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "runningTime=" + runningTime +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask task1 = new MyTask(now + 2000);
        MyTask task2 = new MyTask(now + 4000);
        MyTask task3 = new MyTask(now + 3000);
        MyTask task4 = new MyTask(now + 5000);
        MyTask task5 = new MyTask(now + 1000);

        tasks.put(task1);
        tasks.put(task2);
        tasks.put(task3);
        tasks.put(task4);
        tasks.put(task5);

        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}
