package lambda;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/17 15:56
 */
public class Test7 {
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, null, 3, null, 4);
        list.removeIf(arg -> arg == null);
        System.out.println(list);
    }
}
