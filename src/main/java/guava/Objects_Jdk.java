package guava_test;

import java.util.Objects;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/7 10:46
 */
public class Objects_Jdk {
    public static void main(String[] args) {
        System.out.println(Objects.equals(null, null));
        System.out.println(Objects.equals(1, 1));
        System.out.println(Objects.equals("hello", "hello"));
        System.out.println(Objects.equals(1, "1"));

        String str = null;
        System.out.println(Objects.equals(null, str));

        System.out.println("**************************************************");

        Person p = new Person("张三", "男", 25);
        System.out.println(p.hashCode());
        // 当参数Object对象不为null时，Objects.hash()执行的流程是31*1+Objects.hashCode(); 即在a的hash值上加上31
        System.out.println(Objects.hash(p));
        System.out.println(Objects.hashCode(p));

        System.out.println("**************************************************");

        System.out.println(Objects.toString(p));
        System.out.println(Objects.toString(null, "value is null"));

        System.out.println("**************************************************");
    }

}
