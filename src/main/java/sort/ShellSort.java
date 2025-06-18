package sort;

import java.util.Arrays;

/**
 * Description: 希尔排序
 *
 * @author Baltan
 * @date 2018/11/2 09:56
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr =
                {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15,
                        35, 25, 53, 51};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        double distance = arr.length;
        int arrLength = arr.length;
        /**
         * 将要排序的所有数字按增量d（n/2，n为要排序数的个数）分成若干组，每组中记录的下标相差d，
         * 对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。
         * 当增量减到1时，最后进行一次直接插入排序即可。
         *
         * 49, 38, 65, 97, 76, 13, 27, 49, 78       distance：4
         * 49, 13, 65, 97, 76, 38, 27, 49, 78
         * 49, 13, 27, 97, 76, 38, 65, 49, 78
         * 49, 13, 27, 49, 76, 38, 65, 97, 78
         *
         * 49, 13, 27, 49, 76, 38, 65, 97, 78       distance：2
         * 27, 13, 49, 38, 65, 49, 76, 97, 78
         *
         * 27, 13, 49, 38, 65, 49, 76, 97, 78       distance：1
         * 13, 27, 38, 49, 49, 65, 76, 78, 97
         */
        while (true) {
            distance = Math.ceil(distance / 2);
            int d = (int) distance;
            for (int i = 0; i < d; i++) {
                /**
                 * 进行直接插入排序操作
                 */
                for (int j = i + d; j < arrLength; j += d) {
                    int currNum = arr[j];
                    int k;
                    for (k = j - d; k >= 0 && arr[k] > currNum; k -= d) {
                        arr[k + d] = arr[k];
                    }
                    arr[k + d] = currNum;
                }
            }
            if (d == 1) {
                break;
            }
        }
    }
}