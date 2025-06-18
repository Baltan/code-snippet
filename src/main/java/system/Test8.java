package system;

import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-05-25 17:19
 */
public class Test8 {
    public static void main(String[] args) {
        Map<String, String> map = System.getenv();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "----------" + entry.getValue());
        }
    }
}
