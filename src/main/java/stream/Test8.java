package stream_test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/11/19 16:12
 */
public class Test8 {
    public static void main(String[] args) {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        System.out.println();

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        list.stream().parallel().forEach(x -> System.out.print(x));
        System.out.println();
        list.stream().parallel().forEachOrdered(x -> System.out.print(x));

        System.out.println();

        List<String> words =
                Arrays.asList("aboutjava", "accessibility", "addressing", "addshortcut", "about", "all",
                        "become", "oop");
        int result = words.stream().filter(s -> s.startsWith("a")).mapToInt(s -> s.length()).max().getAsInt();
        System.out.println(result);
    }
}
