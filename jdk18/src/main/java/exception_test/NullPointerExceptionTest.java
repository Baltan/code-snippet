package exception_test;

import lombok.AllArgsConstructor;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/7 17:25
 */
public class NullPointerExceptionTest {
    /**
     * @param args
     * @see
     * <a href="https://www.pdai.tech/md/java/java8up/java14.html#jep-358-%E6%94%B9%E8%BF%9B-nullpointerexceptions-%E6%8F%90%E7%A4%BA%E4%BF%A1%E6%81%AF">JEP 358: 改进 NullPointerExceptions 提示信息</a>
     */
    public static void main(String[] args) {
        D d = new D("test NPE");
        C c = new C(d);
        B b = new B(c);
        A a = new A(b);
        System.out.println(a.b.c.d.content);

        a.b.c = null;
        /**
         * java.lang.NullPointerException: Cannot read field "d" because "a.b.c" is null
         */
        System.out.println(a.b.c.d.content);
    }

    @AllArgsConstructor
    public static class A {
        B b;
    }

    @AllArgsConstructor
    public static class B {
        C c;
    }

    @AllArgsConstructor
    public static class C {
        D d;
    }

    @AllArgsConstructor
    public static class D {
        String content;
    }
}
