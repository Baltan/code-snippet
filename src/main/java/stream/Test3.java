package stream_test;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/24 23:14
 */
public class Test3 {
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(list.stream().allMatch(num -> num > 5));
        System.out.println("------------------------");
        System.out.println(list.stream().anyMatch(num -> num > 5));
        System.out.println("------------------------");
        System.out.println(list.stream().noneMatch(num -> num > 5));
        System.out.println("------------------------");
        System.out.println(list.stream().findFirst().get());
        System.out.println("------------------------");
        System.out.println(list.stream().findAny().get());
        System.out.println("------------------------");
        System.out.println(list.stream().count());
        System.out.println("------------------------");
        System.out.println(list.stream().max(Integer::compare).get());
        System.out.println("------------------------");
        System.out.println(list.stream().min(Integer::compare).get());
        System.out.println("------------------------");
        System.out.println(list.stream().mapToInt(num -> num).sum());
    }
}
