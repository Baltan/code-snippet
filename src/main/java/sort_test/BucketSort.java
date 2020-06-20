package sort_test;

import java.util.*;

/**
 * Description: 桶排序
 *
 * @author Baltan
 * @date 2019-02-15 11:27
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] arr =
                {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15,
                        35, 25, 53, 51};
        bucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bucketSort(int[] arr) {
        /**
         * 根据某个映射规则，将所有待排序的值映射到一系列的桶中，但需保证后一个桶中的值必须都大于前一个桶中的所有值。
         * 例如：值除以10后，结果的整数部分是几就放入几号桶中。
         * 所有数字都映射完后，对每个桶中的数字排序，可以使用JDK自带的对集合的排序。
         * 最后按照桶的顺序依次将桶中的数字依次取出。
         *
         * 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64
         *
         * 桶序号
         *   0
         *   1      13, 12
         *   2      27
         *   3      38, 34
         *   4      49
         *   5
         *   6      65, 64
         *   7      76, 78
         *   8
         *   9      97
         *
         * 桶序号
         *   0
         *   1      12, 13
         *   2      27
         *   3      34, 38
         *   4      49
         *   5
         *   6      64, 65
         *   7      76, 78
         *   8
         *   9      97
         *
         *   12, 13, 27, 34, 38, 49, 64, 65, 76, 78, 97
         */
        int max = arr[0];
        int arrLength = arr.length;
        for (int i = 0; i < arrLength; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        List<Integer>[] bucketArr = new List[max / 10 + 1];

        for (int i = 0; i < max / 10 + 1; i++) {
            bucketArr[i] = new LinkedList<>();
        }

        for (int value : arr) {
            bucketArr[value / 10].add(value);
        }

        for (List<Integer> bucket : bucketArr) {
            Collections.sort(bucket);
        }

        int index = 0;

        for (List<Integer> bucket : bucketArr) {
            for (int value : bucket) {
                arr[index] = value;
                index++;
            }
        }
    }
}
