package reference_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 循环引用的垃圾回收
 *
 * @author Baltan
 * @date 2018/7/18 22:00
 */
public class Test5 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();

        List<A> listA = new ArrayList<>();
        List<B> listB = new ArrayList<>();
        List<C> listC = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            listA.add(a);
            listB.add(b);
            listC.add(c);
        }

        a.setListB(listB);
        b.setListC(listC);
        c.setListA(listA);

        System.gc();
    }
}

class A {
    private List<B> listB;

    public void setListB(List<B> listB) {
        this.listB = listB;
    }

    @Override
    protected void finalize() {
        System.out.println("A被回收了……");
    }
}

class B {
    private List<C> listC;

    public void setListC(List<C> listC) {
        this.listC = listC;
    }

    @Override
    protected void finalize() {
        System.out.println("B被回收了……");
    }
}

class C {
    private List<A> listA;

    public void setListA(List<A> listA) {
        this.listA = listA;
    }

    @Override
    protected void finalize() {
        System.out.println("C被回收了……");
    }
}
