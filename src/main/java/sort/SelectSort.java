package sort_test;

import java.util.Arrays;

/**
 * Description: 简单选择排序
 *
 * @author Baltan
 * @date 2018/11/2 11:08
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr =
                {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15,
                        35, 25, 53, 51};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        int arrLength = arr.length;
        /**
         * 从第一个数字开始，从所有数字中选择一个最小的数字和第一个数字交换。
         * 从第二个数字开始，从所有数字中选择一个最小的数字和第二个数字交换。
         * 直到倒数第二个数和最后一个数字比较交换完成即可。
         *
         * 49, 38, 65, 97, 76, 13
         *
         * 13, 38, 65, 97, 76, 49
         *
         * 13, 38, 65, 97, 76, 49
         *
         * 13, 38, 49, 97, 76, 65
         *
         * 13, 38, 49, 65, 76, 97
         *
         * 13, 38, 49, 65, 76, 97
         */
        for (int i = 0; i < arrLength - 1; i++) {
            int min = arr[i];
            int min_index = i;
            for (int j = i + 1; j < arrLength; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    min_index = j;
                }
            }
            if (min_index != i) {
                int temp = arr[i];
                arr[i] = arr[min_index];
                arr[min_index] = temp;
            }
        }
    }
}
