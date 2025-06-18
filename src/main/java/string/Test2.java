package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2017/12/19 19:28
 */
public class Test2 {
    public static void main(String[] args) {
        Map m = new HashMap<String, Object>();
        m.put("name", "");
        System.out.println((String) m.get("name"));
    }
}
