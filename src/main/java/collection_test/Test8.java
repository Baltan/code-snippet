package collection_test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/12/27 11:06
 */
public class Test8 {
    public static void main(String[] args) {
        Queue<String> queue = new PriorityQueue<>(Comparator.comparingInt(String::length));

        queue.offer("aaaaa");
        queue.offer("aaa");
        queue.offer("a");
        queue.offer("aaaaaaaaa");
        queue.offer("aaaaaaa");

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
