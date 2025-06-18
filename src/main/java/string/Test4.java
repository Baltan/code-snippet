package string;

import java.util.Arrays;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/3/29 14:13
 */
public class Test4 {
    public static void main(String[] args) {
        String str = "a-b-c-d";
        String[] arr = str.split("-");
        System.out.println(Arrays.asList(arr));
    }
}
