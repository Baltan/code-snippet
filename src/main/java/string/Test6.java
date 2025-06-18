package string_test;

import java.lang.reflect.Field;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/9/25 22:50
 */
public class Test6 {
    public static void main(String[] args) throws Exception {
        String str = "abc";
        System.out.println(str);
        Class<? extends String> clazz = str.getClass();
        Field value = clazz.getDeclaredField("value");
        value.setAccessible(true);
        char[] obj = (char[]) value.get(str);
        obj[1] = 's';
//        value.set(str, new char[]{'q'});
        System.out.println(str);
    }
}
