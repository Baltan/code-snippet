package sort_test;

import java.util.Arrays;

/**
 * Description: 计数排序
 *
 * @author Baltan
 * @date 2019-02-15 10:18
 */
public class CountSort {
    public static void main(String[] args) {
        int[] arr =
                {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15,
                        35, 25, 53, 51};
        countSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void countSort(int[] arr) {
        /**
         * 先确定数组中的最大值，建立一个临时数组，临时数组长度为最大值+1。
         * 遍历数组中的所有值，将临时数组中索引等于该值的位置处的值+1。
         * 循环结束后，临时数组的某个索引处的值为多少即代表原数组中该索引值的数字有多少个。
         * 遍历临时数组，重新将数字依次放入原数组即可。
         *
         * 4, 6, 3, 1, 8, 7, 4, 2, 3        原数组
         *
         * 0, 0, 0, 0, 0, 0, 0, 0, 0        临时数组
         * 0, 0, 0, 0, 1, 0, 0, 0, 0
         * 0, 0, 0, 0, 1, 0, 1, 0, 0
         * 0, 0, 0, 1, 1, 0, 1, 0, 0
         * 0, 1, 0, 1, 1, 0, 1, 0, 0
         * 0, 1, 0, 1, 1, 0, 1, 0, 1
         * 0, 1, 0, 1, 1, 0, 1, 1, 1
         * 0, 1, 0, 1, 2, 0, 1, 1, 1
         * 0, 1, 1, 1, 2, 0, 1, 1, 1
         * 0, 1, 1, 2, 2, 0, 1, 1, 1
         *
         * 1, 2, 3, 3, 4, 4, 6, 7, 8
         */
        int max = arr[0];

        for (int value : arr) {
            max = value > max ? value : max;
        }

        int[] countArray = new int[max + 1];

        for (int value : arr) {
            countArray[value]++;
        }

        int index = 0;

        for (int i = 0; i < max + 1; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                arr[index] = i;
                index++;
            }
        }
    }
}
