package pattern.singleton;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: 通过加锁和双重检测实现单例模式，可以实现懒加载
 *
 * @author Baltan
 * @date 2019-03-11 16:20
 */
public class SingletonTest2 {

    private static volatile SingletonTest2 s;

    private SingletonTest2() {
    }

    public static SingletonTest2 getInstance() {
        if (s == null) {
            synchronized (SingletonTest2.class) {
                if (s == null) {
                    s = new SingletonTest2();
                }
            }
        }
        return s;
    }

    /**
     * 当通过ObjectInputputStream反序列化Java对象时，如果对象包含readResolve方法，
     * 就直接返回readResolve方法中的逻辑，否则反序列化会破坏单例模式。
     *
     * @return
     */
    private SingletonTest2 readResolve() {
        return s;
    }

    public static void main(String[] args) {
        Set<SingletonTest2> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(SingletonTest2.getInstance());
        }
        System.out.println(set.size());
    }
}
