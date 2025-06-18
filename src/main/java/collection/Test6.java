package collection;

import java.util.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/11 10:58
 */
public class Test6 {
    public static void main(String[] args) {
        Queue<Integer> queue1 = new ArrayDeque<>();

        queue1.offer(1);
        queue1.offer(2);
        queue1.offer(3);
        queue1.offer(4);
        queue1.offer(3);
        queue1.offer(2);
        System.out.println(queue1);

        queue1.poll();
        queue1.poll();
        System.out.println(queue1);

        System.out.println("********************************");

        Queue<Integer> queue2 = new PriorityQueue<>();

        queue2.offer(1);
        queue2.offer(2);
        queue2.offer(3);
        queue2.offer(4);
        queue2.offer(3);
        queue2.offer(2);
        System.out.println(queue2);

        queue2.poll();
        queue2.poll();
        System.out.println(queue2);

        System.out.println("********************************");

        Queue<Integer> queue3 = new LinkedList<>();
        System.out.println(queue3.peek()); // null
        System.out.println(((LinkedList<Integer>) queue3).getFirst()); // NoSuchElementException
        System.out.println(queue3.element()); // NoSuchElementException
    }
}
