package guava_test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/12 16:00
 */
public class MultimapDemo {
    public static void main(String[] args) {
        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("key1", "value1");
        map.put("key2", "value21");
        map.put("key2", "value22");
        map.put("key2", "value23");
        System.out.println(map);
        System.out.println(map.get("key1"));
        System.out.println(map.get("key2"));
    }
}
