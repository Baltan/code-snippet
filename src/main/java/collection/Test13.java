package collection_test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Description: 比较LinkedList和ArrayList插入大量数据时的性能
 *
 * @author Baltan
 * @date 2020-06-02 16:44
 */
public class Test13 {
    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new ArrayList<>();
        final int COUNT = 10000000;

        long start1 = System.nanoTime();

        for (int i = 0; i < COUNT; i++) {
            list1.add(i);
        }

        long end1 = System.nanoTime();

        long start2 = System.nanoTime();

        for (int i = 0; i < COUNT; i++) {
            list2.add(i);
        }

        long end2 = System.nanoTime();

        System.out.println(end1 - start1);
        System.out.println(end2 - start2);

    }
}
