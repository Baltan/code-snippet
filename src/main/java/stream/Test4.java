package stream_test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/5/1 20:33
 */
public class Test4 {
    public static void main(String[] args) {
        Stream.generate(Math::random).limit(20).forEach(System.out::println);
        System.out.println("==============================");
        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
        System.out.println("==============================");
        List<Integer> nums = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:" + nums.stream().filter(num -> num != null).distinct().
                mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum());
        System.out.println("==============================");
        System.out.println(nums.stream().filter(num -> num != null).
                collect(Collectors.toList()));
        System.out.println("==============================");
        System.out.println("ints sum is:" + nums.stream().filter(num -> num != null).
                reduce((sum, item) -> sum + item).get());
    }
}
