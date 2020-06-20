package stream_test;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/27 10:16
 */
public class Test5 {
    public static void main(String[] args) {
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int value1 = intList.stream().reduce(1, (x, y) -> x * y);
        System.out.println(value1);

        Optional<Integer> value2 = intList.stream().reduce((x, y) -> x * y);
        System.out.println(value2.get());
    }
}
