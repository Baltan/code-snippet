package collection_test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-05-07 15:03
 */
public class Test12 {
    public static void main(String[] args) {
        /**
         * 默认根据插入顺序排序
         */
        Map<String, String> map1 = new LinkedHashMap<>();
        map1.put("1", "1");
        map1.put("3", "3");
        map1.put("5", "5");
        map1.put("7", "7");
        map1.put("2", "2");
        map1.put("4", "4");
        map1.put("6", "6");
        System.out.println(map1);

        /**
         * 根据访问顺序排序
         */
        Map<String, String> map2 = new LinkedHashMap<>(16, 0.75f, true);
        map2.put("1", "1");
        map2.put("3", "3");
        map2.put("5", "5");
        map2.put("7", "7");
        map2.put("2", "2");
        map2.put("4", "4");
        map2.put("6", "6");
        System.out.println(map2);

        map2.get("1");
        map2.get("2");
        map2.get("3");
        map2.get("4");
        map2.get("5");
        map2.get("6");
        map2.get("7");
        System.out.println(map2);
    }
}
