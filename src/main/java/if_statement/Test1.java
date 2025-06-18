package if_statement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Description: 表驱动解决代码中 if…else 过多的问题
 * 参考：<a href="https://zhuanlan.zhihu.com/p/157793899"></a>
 *
 * @author Baltan
 * @date 2020-08-02 23:06
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println(new Test1().test(2));
    }

    private Map<Integer, Function<Integer, Integer>> map = new HashMap<>();

    {
        map.put(1, value -> print1(value));
        map.put(2, value -> print2(value));
        map.put(3, value -> print3(value));
    }

    public int print1(int value) {
        return value * 1;
    }

    public int print2(int value) {
        return value * 2;
    }

    public int print3(int value) {
        return value * 3;
    }

    public int test(int value) {
        return map.get(value).apply(100);
    }
}
