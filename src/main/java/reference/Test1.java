package reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/18 08:55
 */
public class Test1 {
    public static void main(String[] args) {
        Person person = new Person();
        ReferenceQueue<String> rq = new ReferenceQueue();
        WeakReference wf = new WeakReference(person, rq);
        person = null; // 取消对Person对象的强引用
        System.gc();
        Person person1 = (Person) wf.get(); // 假如Person对象没有被回收，person引用Person对象
        System.out.println(person1);
        Reference ref = rq.poll(); // 假如Person对象没有被回收，rq.poll()返回null
        System.out.println(ref);
    }
}

class Person {
    @Override
    public String toString() {
        return "Person{}";
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Person对象被回收了……");
    }
}
