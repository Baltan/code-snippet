package guava_test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/11 21:52
 */
public class StringDemo {
    public static void main(String[] args) {
        CharMatcher digitMatcher = CharMatcher.digit();
        System.out.println(digitMatcher.retainFrom("d5c6s5c7s68cscs7c"));
        System.out.println(digitMatcher.removeFrom("d5c6s5c7s68cscs7c"));

        System.out.println("*************************");

        Joiner joiner = Joiner.on("|").skipNulls();
        Joiner joiner1 = Joiner.on("|").useForNull("666");
        System.out.println(joiner.join(new String[]{"q", " ", "w", null, "e", " ", "r", null, "t", "y"}));
        System.out.println(joiner1.join(new String[]{"q", " ", "w", null, "e", " ", "r", null, "t", "y"}));

        System.out.println("*************************");

        Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();
        System.out.println(splitter.split(" 1,2, ,3,4,5 "));
    }
}
