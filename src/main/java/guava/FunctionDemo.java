package guava;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/12 17:20
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Function<String, String> f1 = str -> str.length() <= 3 ? str : str.substring(0, 3);
        Function<String, String> f2 = str -> str.toUpperCase();
        Function<String, String> f3 = str -> str.trim();

        Function<String, String> composeFunc = Functions.compose(f3, f2);
        Function<String, String> composeFunc2 = Functions.compose(composeFunc, f1);

        ArrayList<String> list = Lists.newArrayList(" abc", " abcdef", " abcd", "qwerty");

        Collection<String> list1 = Lists.transform(list, composeFunc2);
        System.out.println(list1);
    }
}
