package string;

import java.util.Arrays;

/**
 * Description: split test
 *
 * @author Baltan
 * @date 2020-09-04 21:52
 */
public class Test10 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString("1|2|3".split("\\|")));
        System.out.println(Arrays.toString("1|2|3".split("\\|", 3)));
        System.out.println(Arrays.toString("1|2|3".split("\\|", 2)));
        System.out.println(Arrays.toString("1|2|3".split("\\|", 1)));
        System.out.println(Arrays.toString("1|2|".split("\\|")));
        System.out.println(Arrays.toString("1|2|".split("\\|", 3)));
        System.out.println(Arrays.toString("1|2|".split("\\|", 2)));
        System.out.println(Arrays.toString("1|2|".split("\\|", 1)));
        System.out.println(Arrays.toString("|||".split("\\|")));
        System.out.println(Arrays.toString("|||".split("\\|", 4)));
        System.out.println(Arrays.toString("1|2".split("\\|", 10)));
    }
}
