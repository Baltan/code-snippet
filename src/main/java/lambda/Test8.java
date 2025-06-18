package lambda;

import java.util.Arrays;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/17 17:34
 */
public class Test8 {
    public static void main(String[] args) {
        String[] arr = new String[]{"a", "S", "d", "f", "G", "H", "j", "k", "L"};
        Arrays.sort(arr, String::compareToIgnoreCase);
        System.out.println(Arrays.asList(arr));
    }
}
