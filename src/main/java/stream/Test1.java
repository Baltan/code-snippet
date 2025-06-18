package stream_test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/24 22:51
 */
public class Test1 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(arr).skip(3).filter(num -> num >= 6).limit(4).forEach(System.out::println);
        System.out.println("------------------------");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).skip(3).filter(num -> num >= 6).limit(4).forEach(System
                .out::println);
    }
}
