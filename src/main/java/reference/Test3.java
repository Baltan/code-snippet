package reference_test;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/5/3 22:29
 */
public class Test3 {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        /**
         * a和m有对"a"的强引用，wm有对"a"的弱引用
         * b和m有对"b"的强引用，wm有对"b"的弱引用
         */
        String a = new String("a");
        String b = new String("b");
        WeakHashMap wm = new WeakHashMap();
        wm.put(a, "aaa");
        wm.put(b, "bbb");
        HashMap m = new HashMap();
        m.put(a, "aaa");
        m.put(b, "bbb");
        System.out.println(mapper.writeValueAsString(m));
        System.out.println(mapper.writeValueAsString(wm));
        System.out.println("============================");
        /**
         * 以下四行代码执行后，wm有对"a"的弱引用，b和m有对"b"的强引用，此时"a"会被垃圾回收
         */
        m.remove(a);
        wm.remove(b);
        a = null;
        b = null;
        System.gc();
        System.out.println(mapper.writeValueAsString(m));
        System.out.println(mapper.writeValueAsString(wm));
    }
}
