package collection;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019/1/10 16:51
 */
public class Test9 {
    public static void main(String[] args) {
        Set<Integer> set = new LinkedHashSet<>();

        set.add(1);
        set.add(3);
        set.add(5);
        set.add(2);
        set.add(4);
        set.add(6);
        set.add(3);
        set.add(1);
        set.add(2);
        set.add(5);
        set.add(6);
        set.add(4);

        System.out.println(set);
    }
}
