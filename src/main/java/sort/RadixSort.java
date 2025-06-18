package sort_test;

import java.util.*;

/**
 * Description: 基数排序
 *
 * @author Baltan
 * @date 2019-02-13 15:55
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr =
                {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15,
                        35, 25, 53, 51};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        /**
         * 准备10个队列，序号分别为0-9。
         * 先根据数组中所有数字的个位将数字放到对应的队列中，个位是几，就放到几号队列中。
         * 所有数字都放入队列中后，将所有队列中的数字按照队列的序号依次取出。
         * 再根据数组中所有数字的十位将数字放到对应的队列中，十位是几，就放到几号队列中。
         * 所有数字都放入队列中后，将所有队列中的数字按照队列的序号依次取出。
         * 再根据数组中所有数字的百位将数字放到对应的队列中，百位是几，就放到几号队列中。
         * 所有数字都放入队列中后，将所有队列中的数字按照队列的序号依次取出。
         * ……
         *
         * 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64
         *
         * 队列序号  0   1   2   3   4   5   6   7   8   9
         *                 12  13  34  65  76  97  38  49
         *                         64          27  78  49
         *
         * 12, 13, 34, 64, 65, 76, 97, 27, 38, 78, 49, 49
         *
         * 队列序号  0   1   2   3   4   5   6   7   8   9
         *             12  27  34  49      64  76      97
         *             13      38  49      65  78
         *
         * 12, 13, 27, 34, 38, 49, 49, 64, 65, 76, 78, 97
         */
        int max = arr[0];
        int arrLength = arr.length;
        Queue<Integer>[] queueArr = new Queue[10];
        for (int i = 0; i < arrLength; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        /**
         * int maxLength = (max+"").length();
         */
        int maxLength = 0;
        while (max > 0) {
            max /= 10;
            maxLength++;
        }

        for (int i = 0; i < 10; i++) {
            queueArr[i] = new LinkedList<>();
        }

        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < arr.length; j++) {
                int currNum = arr[j];
                int num = (int) (currNum % Math.pow(10, i + 1) / Math.pow(10, i));
                queueArr[num].offer(currNum);
            }

            int index = 0;
            for (int j = 0; j < 10; j++) {
                Queue<Integer> currQueue = queueArr[j];
                while (!currQueue.isEmpty()) {
                    arr[index] = currQueue.poll();
                    index++;
                }
            }
        }
    }
}
