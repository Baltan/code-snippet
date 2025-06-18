package sort.lajw;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: 雷达图重排序
 *
 * @author Baltan
 * @date 2020-11-09 15:11
 */
public class CircleReorder {
    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 3, 2);
        System.out.println(reorder(l1));

        List<Integer> l2 = Arrays.asList(1, 3, 2, 4);
        System.out.println(reorder(l2));

        List<Integer> l3 = Arrays.asList(1, 3, 2, 4, 5);
        System.out.println(reorder(l3));

        List<Integer> l4 = Arrays.asList(1, 3, 2, 4, 6, 5);
        System.out.println(reorder(l4));

        List<Integer> l5 = Arrays.asList(1, 3, 2, 4, 5, 6, 7);
        System.out.println(reorder(l5));

        List<Integer> l6 = Arrays.asList(1, 3, 2, 4, 5, 6, 7, 8);
        System.out.println(reorder(l6));

        List<Integer> l7 = Arrays.asList(1, 3, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        System.out.println(reorder(l7));
    }

    /**
     * 将strList中较长的字符串放在靠近12点和6点的位置
     *
     * @param strList
     * @return
     */
    private static List<Integer> reorder(List<Integer> strList) {
        if (CollectionUtils.isEmpty(strList) || strList.size() < 3) {
            return strList;
        }

        int size = strList.size();
        Integer[] result = new Integer[size];
        /**
         * 按照字符串长度升序排列
         */
        Collections.sort(strList, Comparator.comparingInt(x -> x));
        /**
         * 6点位置索引
         */
        int mid = size / 2;
        result[0] = strList.get(size - 1);
        result[mid] = strList.get(size - 2);
        /**
         * [1点位置,7点位置,5点位置,11点位置]对应雷达图上的位置为[EN,WS,ES,WN]
         *
         * 考虑当元素总个数为奇数时，mid会向雷达图的左侧偏一个位置，则12点和6点位置安排好后，雷达图的左半部
         * 分可能比右半部分少一个位置，所以总是先放雷达图的右半边的位置，再放雷达图左半边的位置
         */
        int[] indexArray = {size - 1, mid - 1, mid + 1, 1};
        int index = 0;

        for (int i = size - 3; i >= 0; i--) {
            result[indexArray[index]] = strList.get(i);
            /**
             * 东北和西南部分索引减，东南和西北部分索引增
             */
            if (index == 0 || index == 1) {
                indexArray[index]--;
            } else {
                indexArray[index]++;
            }
            index = (index + 1) % 4;
        }
        return Arrays.stream(result).collect(Collectors.toList());
    }
}
