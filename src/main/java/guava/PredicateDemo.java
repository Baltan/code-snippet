package guava;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.Collection;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/12 22:45
 */
public class PredicateDemo {
    public static void main(String[] args) {
        Collection<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Predicate<Integer> pre = num -> num % 2 == 0;
        Collection<Integer> newList = Collections2.filter(list, pre);
        System.out.println(newList);
    }
}
