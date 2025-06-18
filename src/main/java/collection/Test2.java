package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/5/3 22:09
 */
public class Test2 {
    public static void main(String[] args) {
        Map m1 = new HashMap();
        A a1 = new A();
        A a2 = new A();
        A a = new A();
        m1.put(a1, "a1");
        m1.put(a2, "a2");
        System.out.println(a1.equals(a2));
        System.out.println(m1.size());
        System.out.println(m1.get(a));
    }
}

class A {
    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
//        return (int) (Math.random() * Integer.MAX_VALUE);
        return 1;
    }
}
