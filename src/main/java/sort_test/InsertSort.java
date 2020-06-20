package sort_test;

import java.util.Arrays;

/**
 * Description: 直接插入排序
 *
 * @author Baltan
 * @date 2018/11/2 09:39
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr =
                {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15,
                        35, 25, 53, 51};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        int arrLength = arr.length;
        for (int i = 1; i < arrLength; i++) {
            int currNum = arr[i];
            int j;
            /**
             * 要插入的数字从第二个开始，此时保证要插入的数字前面的所有数字都是已经排好序的。
             * 每个要插入的数字都和它之前的数字逐一比较，若前面的数字大于当前数字，
             * 就把前面的数字向后挪一个位置，直到前面没有数字或者前面的某一个数字小于等于当前要插入的数字为止，
             * 最后把当前要插入的数字放在对应的位置即可。
             *
             * 49, 38, 65, 97, 76, 13       currNum：38
             * 49, 49, 65, 97, 76, 13
             * 38, 49, 65, 97, 76, 13
             *
             * 38, 49, 65, 97, 76, 13       currNum：65
             *
             * 38, 49, 65, 97, 76, 13       currNum：97
             *
             * 38, 49, 65, 97, 76, 13       currNum：76
             * 38, 49, 65, 97, 97, 13
             * 38, 49, 65, 76, 97, 13
             *
             * 38, 49, 65, 76, 97, 13       currNum：13
             * 38, 49, 65, 76, 97, 97
             * 38, 49, 65, 76, 76, 97
             * 38, 49, 65, 65, 76, 97
             * 38, 49, 49, 65, 76, 97
             * 38, 38, 49, 65, 76, 97
             * 13, 38, 49, 65, 76, 97
             */
            for (j = i - 1; j >= 0 && arr[j] > currNum; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = currNum;
        }
    }
}
