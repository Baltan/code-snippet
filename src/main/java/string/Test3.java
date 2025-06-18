package string;

import java.util.Objects;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/3/7 16:53
 */
public class Test3 {
    public static void main(String[] args) {
        String str1 = "Java";
        String str2 = "Java";
        String str3 = new String("Java");
        System.out.println(str1 == str3);
        System.out.println(str1 == str3.intern());
        System.out.println(Objects.equals(str1, str3));

        System.out.println("------------------------------------");

        /**
         * 引用：<a href="https://blog.csdn.net/langjian2012/article/details/39975723"></a>
         */
        String a = "a";
        String b = "b";
        String ab = "ab";
        final String aFinal = "a";
        String result = aFinal + "b";
        String plus = a + "b";
        /**
         * a+b是两个变量相加，需要到运行时才能确定其值，到运行时后JVM会为两者相加后产生一个新的对象，
         * 因此a+b==ab的结果为false。
         */
        System.out.println((a + b) == ab); // false
        /**
         * "a"+"b"是常量，在编译时JVM已经将其变为"ab"字符串了，而ab="ab"也是常量，这两者在常量池即
         * 为同一地址，因此("a"+"b")==ab为true。
         */
        System.out.println(("a" + "b") == ab); // true
        /**
         * result=aFinal+"b"，aFinal是个final的变量， result在编译时也已经被转变为了"ab"，和"ab"
         * 在常量池中同样为同一地址，因此result==ab为true。
         */
        System.out.println(result == ab); // true
        /**
         * plus和a+b的情况是相同的，因此plus==ab为false。
         */
        System.out.println(plus == ab); // false
        /**
         * 调用了plus.intern()方法，这个方法的作用是获取plus指向的常量池地址，因此plus.intern()==
         * ab为true。
         */
        System.out.println(plus.intern() == ab); // true
    }
}
