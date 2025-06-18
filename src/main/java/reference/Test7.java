package reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2023/8/5 00:27
 */
public class Test7 {
    @SneakyThrows
    public static void main(String[] args) {
        Queue<Item> queue = new LinkedList<>();

        for (int i = 1; i <= 10000; i++) {
            queue.offer(new Item(i));
        }

        while (!queue.isEmpty()) {
            queue.poll();
            TimeUnit.SECONDS.sleep(3L);
            System.gc();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Item {
        private int order;

        @Override
        protected void finalize() {
            System.out.println("对象" + order + "被回收了……");
        }
    }
}
