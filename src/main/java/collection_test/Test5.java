package collection_test;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/11 10:57
 */
public class Test5 {
    public static void main(String[] args) {
        Map<String, String> hm = new HashMap<>();
        Map<String, String> ihm = new IdentityHashMap<>();
        hm.put("a", "a");
        hm.put("a", "b");
        hm.put(new String("a"), "c");

        ihm.put("a", "a");
        ihm.put("a", "b");
        ihm.put(new String("a"), "c");

        System.out.println(hm);
        System.out.println(ihm);
    }
}
