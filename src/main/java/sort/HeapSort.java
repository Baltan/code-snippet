package sort_test;

import java.util.Arrays;

/**
 * Description: 堆排序
 *
 * @author Baltan
 * @date 2019-02-14 22:55
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr =
                {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15,
                        35, 25, 53, 51};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        /**
         * 从最后一个非叶子节点开始依次向前调整二叉树，直到根节点，此时，二叉树被调整为一个大顶堆。
         * 最后一个叶子节点的索引为arrLength-1，则最后一个非叶子节点的索引为(arrLength-1-1)/2=arrLength/2-1。
         * 调整完后的大顶堆二叉树的根节点数值（索引为0）即时最大值，将其与数组中最后一个值（索引为arrLength-1）交换，
         * 固定数组最后一个值，将数组的剩余部分从根节点开始重新通过交换节点创建大顶堆。
         * 调整完后的大顶堆二叉树的根节点数值（索引为0）即时最大值，将其与数组中倒数第二个值（索引为arrLength-2）交换，
         * 依次循环直到数组第一个元素。
         */
        int arrLength = arr.length;
        int startIndex = arrLength / 2 - 1;
        for (int i = startIndex; i >= 0; i--) {
            transformIntoMaxHeap(arr, arrLength, i);
        }
        for (int i = arrLength - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            transformIntoMaxHeap(arr, i, 0);
        }
    }

    public static void transformIntoMaxHeap(int[] arr, int arrLength, int nodeIndex) {
        /**
         * 对于某一个非叶子结点，如果其在数组中对应的索引为nodeIndex，并且假设它的左、右子节点存在，
         * 则其左子节点的索引为nodeIndex*2+1，右子节点的索引为nodeIndex*2+2。
         * 找到这三个节点中的最大值，通过交换节点位置，将最大值交换到双亲节点的位置。
         * 若发生了节点交换，则最大值原来所在位置对应的大顶堆子树可能被破坏，需要递归重新调整。
         */
        int leftNodeIndex = nodeIndex * 2 + 1;
        int rightNodeIndex = nodeIndex * 2 + 2;
        int maxValueIndex = nodeIndex;

        if (leftNodeIndex < arrLength && arr[leftNodeIndex] > arr[maxValueIndex]) {
            maxValueIndex = leftNodeIndex;
        }
        if (rightNodeIndex < arrLength && arr[rightNodeIndex] > arr[maxValueIndex]) {
            maxValueIndex = rightNodeIndex;
        }
        if (maxValueIndex != nodeIndex) {
            int temp = arr[nodeIndex];
            arr[nodeIndex] = arr[maxValueIndex];
            arr[maxValueIndex] = temp;

            transformIntoMaxHeap(arr, arrLength, maxValueIndex);
        }
    }
}
