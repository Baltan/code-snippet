package guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/12 16:46
 */
public class BiMapDemo {
    public static void main(String[] args) {
        BiMap<String, String> map = HashBiMap.create();
        map.put("key1", "value1");
//        map.put("key1", "value2");
//        map.put("key2", "value1"); // java.lang.IllegalArgumentException: value already present: value1
        map.forcePut("key2", "value1");
        System.out.println(map);
        System.out.println(map.inverse());
    }
}
