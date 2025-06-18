package reference;

import java.lang.ref.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: 引用：<a href="https://blog.csdn.net/panyongcsd/article/details/46605613"></a>
 *
 * @author Baltan
 * @date 2018/7/18 09:00
 */
public class Test2 {
    private static ReferenceQueue rq = new ReferenceQueue();

    public static void checkQueue() {
        Reference inq = rq.poll();
        // 从队列中取出一个引用
        if (inq != null) {
            System.out.println("In queue: " + inq + " : " + inq.get());
        }
    }

    public static void main(String[] args) {
        final int size = 10;
        // 创建10个Grocery对象以及10个软引用
        Set sa = new HashSet();

        for (int i = 0; i < size; i++) {
            SoftReference ref = new SoftReference(new Grocery("soft " + i), rq);
            System.out.println("Just created soft: " + ref.get());
            sa.add(ref);
        }

        System.gc();
        checkQueue();
        System.out.println("---------------------------------------------------");

        // 创建10个Grocery对象以及10个弱引用
        Set wa = new HashSet();

        for (int i = 0; i < size; i++) {
            WeakReference ref = new WeakReference(new Grocery("weak " + i), rq);
            System.out.println("Just created weak: " + ref.get());
            wa.add(ref);
        }

        System.gc();
        checkQueue();
        System.out.println("---------------------------------------------------");

        // 创建10个Grocery对象以及10个虚引用
        Set pa = new HashSet();

        for (int i = 0; i < size; i++) {
            PhantomReference ref = new PhantomReference(new Grocery("Phantom " + i), rq);
            System.out.println("Just created Phantom: " + ref.get());
            pa.add(ref);
        }

        System.gc();
        checkQueue();
    }
}

class Grocery {
    private static final int SIZE = 10000; // 属性d使得每个Grocery对象占用较多内存，有80K左右
    private double[] d = new double[SIZE];
    private String id;

    public Grocery(String id) {
        this.id = id;
    }

    public String toString() {
        return id;
    }

    public void finalize() {
        System.out.println("Finalizing " + id);
    }
}