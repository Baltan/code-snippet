package algorithm;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-21 14:11
 */
public class BinarySearchTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(binarySearch(arr, 1));
        System.out.println(binarySearch(arr, 5));
        System.out.println(binarySearch(arr, 6));
        System.out.println(binarySearch(arr, 10));
        System.out.println(binarySearch(arr, 11));
    }

    public static int binarySearch(int[] arr, int target) {
        int length = arr.length;
        int lo = 0;
        int hi = length - 1;
        while (lo <= hi) {
            int position = (lo + hi) / 2;
            if (arr[position] == target) {
                return position;
            } else if (arr[position] < target) {
                lo = position + 1;
            } else {
                hi = position - 1;
            }
        }
        return -1;
    }
}
