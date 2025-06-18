package pattern.singleton;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: 通过内部类实现单例模式，既不用加锁，也能实现懒加载
 *
 * @author Baltan
 * @date 2019-03-11 16:13
 */
public class SingletonTest1 {
    private SingletonTest1() {
    }

    private static class Inner {
        private static SingletonTest1 s = new SingletonTest1();
    }

    public static SingletonTest1 getInstance() {
        return Inner.s;
    }

    /**
     * 当通过ObjectInputputStream反序列化Java对象时，如果对象包含readResolve方法，
     * 就直接返回readResolve方法中的逻辑，否则反序列化会破坏单例模式。
     *
     * @return
     */
    private SingletonTest1 readResolve() {
        return Inner.s;
    }

    public static void main(String[] args) {
        Set<SingletonTest1> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(SingletonTest1.getInstance());
        }
        System.out.println(set.size());
    }
}
