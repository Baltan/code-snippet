package guava;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/6 23:16
 */
public class Objects_Guava {
    public static void main(String[] args) {
        System.out.println(Objects.equal(null, null));
        System.out.println(Objects.equal(1, 1));
        System.out.println(Objects.equal("hello", "hello"));
        System.out.println(Objects.equal(1, "1"));

        String str1 = null;
        System.out.println(Objects.equal(null, str1));

        System.out.println("**************************************************");

        Person p = new Person("张三", "男", 25);
        System.out.println(Objects.hashCode(p));
    }
}
