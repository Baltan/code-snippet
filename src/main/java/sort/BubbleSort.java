package sort;

import java.util.Arrays;

/**
 * Description: 冒泡排序
 *
 * @author Baltan
 * @date 2018/11/2 11:24
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr =
                {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15,
                        35, 25, 53, 51};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        int arrLength = arr.length;
        /**
         * 先以第一个数字为终点，从尾部开始，每两个相邻的数字相互比较，如果前面的数字大于后面的数字就交换两数，
         * 循环结束后，第一个位置上为最小的数字。
         * 再以第二个数字为终点，从尾部开始，每两个相邻的数字相互比较，如果前面的数字大于后面的数字就交换两数，
         * 循环结束后，第二个位置上为第二小的数字。
         * 直到以倒数第二个数字为基准，和最后一个数字比较交换完成即可。
         *
         * 49, 38, 65, 97, 76, 13
         * 49, 38, 65, 97, 13, 76
         * 49, 38, 65, 13, 97, 76
         * 49, 38, 13, 65, 97, 76
         * 49, 13, 38, 65, 97, 76
         * 13, 49, 38, 65, 97, 76
         *
         * 13, 49, 38, 65, 76, 97
         * 13, 38, 49, 65, 76, 97
         */
        for (int i = 0; i < arrLength - 1; i++) {
            for (int j = arrLength - 2; j >= i; j--) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
