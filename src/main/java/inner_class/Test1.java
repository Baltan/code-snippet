package inner_class;

import overload.Test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/5/2 17:34
 */
public class Test1 {
    public static void main(String[] args) {
        new Test1().func();
    }

    private int s1 = 1;
    private final int s2 = 2;

    public void func() {
        int s3 = 3;
        final int s4 = 4;// 只能访问局部常量(JDK1.8之前)

        class A {
            public void func() {
                System.out.println(s1);
                System.out.println(s2);
                System.out.println(s3);
                System.out.println(s4);

//                s3++; // s3被编译器默认为final类型
            }
        }
        new A().func();
    }
}
