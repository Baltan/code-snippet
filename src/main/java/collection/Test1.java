package collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/5/3 21:59
 */
public class Test1 {
    public static void main(String[] args) {
        Set s1 = new HashSet();
        System.out.println(s1.add(1));
        System.out.println(s1.add(1));
        System.out.println("============================");
        List l = Arrays.asList(1, 2, 3, 2, 1);
        Set s2 = new HashSet(l);
        System.out.println(s2.size());
    }
}
