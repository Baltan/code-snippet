package collection;

import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/11/23 15:01
 */
public class Test7 {
    public static void main(String[] args) {
        List<Integer> list = Ints.asList(1, 2, 3, 4, 5);

        Object[] arr1 = list.toArray();
        System.out.println(Arrays.toString(arr1));

        Integer[] arr2 = list.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arr2));

        Integer[] arr3 = new Integer[list.size()];
        list.toArray(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
