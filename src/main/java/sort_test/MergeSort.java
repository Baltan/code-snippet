package sort_test;

import java.util.Arrays;

/**
 * Description: 归并排序
 *
 * @author Baltan
 * @date 2019-02-13 14:33
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr =
                {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15,
                        35, 25, 53, 51};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int lo, int hi) {
        int mid = (lo + hi) / 2;
        /**
         * 递归条件：当开始索引小于结束索引时，否则说明两个索引重合，即只有一个数字无需再递归
         */
        if (lo < hi) {
            /**
             * 将数组的两个子数组分别排序后，执行归并操作
             */
            mergeSort(arr, lo, mid);
            mergeSort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }
    }

    public static void merge(int[] arr, int lo, int mid, int hi) {
        /**
         * 将一个数组分成两段，可以看做是两个子数组。新建一个临时数组用于存放后续操作的数字。
         * 维护两个指针，分别指向两个子数组的第一个数字。
         * 比较两个指针指向的数字，将较小的一个放入临时数组，向后移动指向较小数字的指针。
         * 如此循环，直到其中一个子数组中的数字都被放入临时数组，将另一个子数组中的数字也都放入到临时数组。
         * 最后将临时数组中的数字放回原数组中对应的位置
         *
         * 49, 38, 65, 76, 13, 27, 49, 78, 97       临时数组
         *
         * 49, 38, 65, 97, 76, 13, 27, 49, 78
         * ☞               ☞
         * 49, 38, 65, 97, 76, 13, 27, 49, 78
         *     ☞           ☞
         * 49, 38, 65, 97, 76, 13, 27, 49, 78
         *         ☞       ☞
         * 49, 38, 65, 97, 76, 13, 27, 49, 78
         *             ☞   ☞
         * 49, 38, 65, 97, 76, 13, 27, 49, 78
         *             ☞       ☞
         * 49, 38, 65, 97, 76, 13, 27, 49, 78
         *             ☞           ☞
         * 49, 38, 65, 97, 76, 13, 27, 49, 78
         *             ☞               ☞
         * 49, 38, 65, 97, 76, 13, 27, 49, 78
         *             ☞                   ☞
         * 49, 38, 65, 97, 76, 13, 27, 49, 78
         *             ☞                       ☞
         */
        int[] tempArr = new int[hi - lo + 1];
        int index = 0;
        int firstCursor = lo;
        int secondCursor = mid + 1;

        while (firstCursor <= mid && secondCursor <= hi) {
            if (arr[firstCursor] <= arr[secondCursor]) {
                tempArr[index] = arr[firstCursor];
                firstCursor++;
            } else {
                tempArr[index] = arr[secondCursor];
                secondCursor++;
            }
            index++;
        }
        while (firstCursor <= mid) {
            tempArr[index] = arr[firstCursor];
            firstCursor++;
            index++;
        }
        while (secondCursor <= hi) {
            tempArr[index] = arr[secondCursor];
            secondCursor++;
            index++;
        }
        for (int i = 0, tempArrLength = tempArr.length; i < tempArrLength; i++) {
            arr[i + lo] = tempArr[i];
        }
    }
}
