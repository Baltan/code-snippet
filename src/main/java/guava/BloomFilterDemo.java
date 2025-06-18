package guava_test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * Description: 参考：<a href="https://juejin.im/post/5db69365518825645656c0de"></a>
 *
 * @author Baltan
 * @date 2019-11-11 14:15
 */
public class BloomFilterDemo {
    public static void main(String[] args) {
        /**
         * 计算错误匹配的数据的数量
         */
        int count = 0;
        final int DATA_COUNT = 1000000;
        final int OFFSET = 20000;
        /**
         * 布隆过滤器默认错误率为0.03
         */
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), DATA_COUNT);
        /**
         * 在过滤器中存入数据[0,DATA_COUNT)，共计DATA_COUNT条数据
         */
        for (int i = 0; i < DATA_COUNT; i++) {
            filter.put(i);
        }
        /**
         * 测试数据[0,DATA_COUNT)是否在过滤器中
         */
        for (int i = 0; i < DATA_COUNT; i++) {
            if (!filter.mightContain(i)) {
                System.out.println("数据 " + i + " 没有匹配上");
            }
        }
        /**
         * 测试数据[DATA_COUNT,DATA_COUNT + OFFSET)是否在过滤器中
         */
        for (int i = DATA_COUNT; i < DATA_COUNT + OFFSET; i++) {
            if (filter.mightContain(i)) {
                count++;
            }
        }
        System.out.println("错误匹配的数量： " + count);
        System.out.println("错误匹配的比例： " + 1.0 * count / OFFSET);
    }
}
