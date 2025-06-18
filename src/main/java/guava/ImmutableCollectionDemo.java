package guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/7 14:05
 */
public class ImmutableCollectionDemo {
    public static void main(String[] args) {
        ImmutableMap<String, String> map1 = ImmutableMap.of("key1", "value1", "key2", "value2", "key3",
                "value3", "key4", "value4", "key5", "value5");
        System.out.println(map1.size());
        System.out.println(map1.keySet());
        System.out.println(map1.values());
        System.out.println(map1.entrySet());

        System.out.println("**************************************");

        ImmutableSet<String> set1 = ImmutableSet.of("value1", "value2", "value3", "value4");
        System.out.println(set1.size());
        System.out.println(set1);

        System.out.println("**************************************");

        ImmutableList<String> list1 = set1.asList();
        System.out.println(list1.size());
        System.out.println(list1.reverse());

        System.out.println("**************************************");

        List<Integer> list2 = new ArrayList<>();
        ImmutableList<Integer> list3 = ImmutableList.copyOf(list2);
        System.out.println(list2.equals(list3));
        list2.add(1);
        System.out.println(list2.size());
        System.out.println(list3.size());
    }
}
