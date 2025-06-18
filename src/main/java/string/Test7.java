package string;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/12/24 11:08
 */
public class Test7 {
    public static void main(String[] args) {
        String str = "a.b.c.d";
        System.out.println(str.replaceAll(".", "/"));
        System.out.println(str.replaceAll(Pattern.quote("."), "/"));
        System.out.println(str.replaceAll(Pattern.quote("."), File.separator));
        System.out.println(str.replaceAll(Pattern.quote("."), Matcher.quoteReplacement(File.separator)));
        System.out.println(str.replace(".", File.separator));
    }
}
