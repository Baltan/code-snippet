package stream_test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/7 16:13
 */
public class DropWhileTest {
    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5);
        System.out.println(list);

        list = list.stream()
                /**
                 * 遍历stream，当元素小于3时就舍弃，直到第一个不小于3的元素，停止继续处理，得到[3,4,5]
                 */
                .dropWhile(x -> x < 3)
                /**
                 * 遍历stream，当元素小于5时就保留，直到第一个不小于5的元素，停止继续处理，得到[3,4]
                 */
                .takeWhile(x -> x < 5)
                .collect(Collectors.toList());
        System.out.println(list);
    }
}
