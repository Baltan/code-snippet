package collection;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.ListIterator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-15 19:13
 */
public class Test10 {
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(11, 22, 33, 44, 55, 66, 77, 88, 99);
        ListIterator<Integer> listIterator = list.listIterator();

        while (listIterator.hasNext()) {
            System.out.println(listIterator.nextIndex());
            System.out.println(listIterator.next());
        }
        System.out.println("-----------------------");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previousIndex());
            System.out.println(listIterator.previous());
//            listIterator.set(666);
        }
    }
}
