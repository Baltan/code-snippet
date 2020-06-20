package guava_test;


import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Iterator;
import java.util.Set;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/11 10:22
 */
public class MultisetDemo {
    public static void main(String[] args) {
        Multiset<Integer> multiset = HashMultiset.create();
        multiset.add(1);
        multiset.add(2);
        multiset.add(3);
        multiset.add(4);
        multiset.add(2);
        multiset.add(3);
        multiset.add(1);
        System.out.println(multiset.size());
        System.out.println("1的个数为" + multiset.count(1));
        System.out.println("*******************************");
        Set<Multiset.Entry<Integer>> set = multiset.entrySet();
        System.out.println(set);
        Iterator<Multiset.Entry<Integer>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Multiset.Entry<Integer> ele = iterator.next();
            System.out.println(ele);
            System.out.println(ele.getElement() + "的个数为" + ele.getCount());
        }
    }
}
