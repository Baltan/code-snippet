package stream_test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/7 16:37
 */
public class FlatMappingTest {
    public static void main(String[] args) {
        var list = List.of("a", "ab", "abc");
        var result = list.stream()
                .collect(Collectors.flatMapping(v -> v.chars().boxed(),
                        Collectors.toList()));
        System.out.println(result);
    }
}
