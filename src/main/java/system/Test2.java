package system;

import java.util.Arrays;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/18 10:06
 */
public class Test2 {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 4, 5, 6};
        int[] arr2 = new int[6];
        System.arraycopy(arr1, 0, arr2, 0, 6);
        int[] arr3 = Arrays.copyOf(arr1, 6);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
    }
}
