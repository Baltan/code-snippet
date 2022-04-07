package collection_test;

import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/7 16:10
 */
public class ImmutableListTest {
    public static void main(String[] args) {
        /**
         * 不可变集合
         */
        var list = List.of(1, 2, 3);
        System.out.println(list);
        /**
         * java.lang.UnsupportedOperationException
         */
        list.add(4);
        System.out.println(list);
    }
}
