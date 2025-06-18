package object_test;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Description:
 *
 * @author baltan
 * @date 2023/12/13 11:32
 */
public class CopyTest {
    public static void main(String[] args) {
        A a = new A()
                .setX(ListUtil.of(1, 2, 3, 4))
                .setB(new B().setX(100))
                .setCs(ListUtil.of(new C().setX(1000), new C().setX(2000)))
                .setD(new D().setE(new E().setX(10000)));

        OtherA otherA = new OtherA();
        BeanUtil.copyProperties(a, otherA);
        System.out.println("a：\n" + a);
        System.out.println("otherA：\n" + otherA);

        System.out.println("------------------------------------");

        otherA.getX().add(8888);
        System.out.println("a：\n" + a);
        System.out.println("otherA：\n" + otherA);

        System.out.println("------------------------------------");

        A a1 = new A();
        BeanUtil.copyProperties(a, a1);
        System.out.println("a：\n" + a);
        System.out.println("a1：\n" + a1);

        System.out.println("------------------------------------");

        a1.getX().add(9999);
        System.out.println("a：\n" + a);
        System.out.println("a1：\n" + a1);
    }

    @Data
    @Accessors(chain = true)
    public static class A {
        private List<Integer> x;
        private B b;
        private List<C> cs;
        private D d;
    }

    @Data
    @Accessors(chain = true)
    public static class B {
        private int x;
    }

    @Data
    @Accessors(chain = true)
    public static class C {
        private int x;
    }

    @Data
    @Accessors(chain = true)
    public static class D {
        private E e;
    }

    @Data
    @Accessors(chain = true)
    public static class E {
        private int x;
    }

    @Data
    @Accessors(chain = true)
    public static class OtherA {
        private List<Integer> x;
        private OtherB b;
        private List<OtherC> cs;
        private OtherD d;
    }

    @Data
    @Accessors(chain = true)
    public static class OtherB {
        private int x;
    }

    @Data
    @Accessors(chain = true)
    public static class OtherC {
        private int x;
    }

    @Data
    @Accessors(chain = true)
    public static class OtherD {
        private OtherE e;
    }

    @Data
    @Accessors(chain = true)
    public static class OtherE {
        private int x;
    }
}
