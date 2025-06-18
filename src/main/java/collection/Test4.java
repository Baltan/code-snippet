package collection;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/7 21:15
 */
public class Test4 {
    public static void main(String[] args) {
        SortedSet<Integer> set = new TreeSet<>();
        set.add(4);
        set.add(2);
        set.add(3);
        set.add(1);
        System.out.println(set);
        System.out.println(set.first());
        System.out.println(set.last());
        System.out.println(set.headSet(3));
        System.out.println(set.subSet(2, 4));
    }
}
