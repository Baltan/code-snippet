package stream;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/24 22:57
 */
public class Test2 {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("eee", "ddd", "aaa", "bbb", "ccc");
        list.stream().map(Test2::filterChar).forEach(System.out::println);
        System.out.println("---------------------------------------------------");
        list.stream().sorted().flatMap(Test2::filterChar).forEach(System.out::println);
    }

    public static Stream<String> filterChar(String str) {
        return Arrays.stream(str.split(""));
    }
}
