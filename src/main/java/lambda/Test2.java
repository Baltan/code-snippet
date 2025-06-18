package lambda;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/5/1 14:44
 */
public class Test2 {
    public static void main(String[] args) {
        String[] strArray = {"a", "A", "a", "a", "A", "A", "a"};
        List<String> strList = Arrays.asList(strArray);
        Predicate<String> matched = s -> s.equals("a");
        getMatchedStrings(strList, matched);
        getMatchedStrings(strList, Test2::isUpperCaseA);
    }

    public static void getMatchedStrings(List<String> list, Predicate<String> pre) {
        List<String> newList = new ArrayList<>();
        for (String str : list) {
            if (pre.test(str)) {
                newList.add(str);
            }
        }
        System.out.println(newList);
    }

    public static boolean isUpperCaseA(String str) {
        return str.equals("A");
    }
}
