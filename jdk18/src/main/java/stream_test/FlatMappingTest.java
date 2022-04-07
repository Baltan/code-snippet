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
    /**
     * @param args
     * @see
     * <a href="https://www.pdai.tech/md/java/java8up/java9.html#%E9%9B%86%E5%90%88stream-%E5%92%8C-optional"></a>
     */
    public static void main(String[] args) {
        var list = List.of("a", "ab", "abc");
        var result = list.stream()
                .collect(Collectors.flatMapping(v -> v.chars().boxed(),
                        Collectors.toList()));
        System.out.println(result);
    }
}
