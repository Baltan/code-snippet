package guava_test;

import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/11 22:13
 */
public class IntegerDemo {
    public static void main(String[] args) {
        List<Integer> list = Ints.asList(1, 2, 3, 4, 5, 6);
        System.out.println(list);

        String str = Ints.join(",", 1, 2, 3, 4, 5);
        System.out.println(str);

        int[] arr = Ints.concat(new int[]{1, 2}, new int[]{3, 4}, new int[]{5, 6});
        System.out.println(arr.length);

        System.out.println(Ints.max(arr) + "," + Ints.min(arr));

        System.out.println(Ints.contains(arr, 5));

        int[] arr1 = Ints.toArray(list);
        System.out.println(arr1.length);
    }
}
