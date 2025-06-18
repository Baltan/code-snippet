package pattern.singleton;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: 通过枚举实现单例模式，可以避免通过反射或反序列化创建实例，但不能延时加载
 *
 * @author Baltan
 * @date 2019-03-11 19:00
 */
public enum SingletonTest3 {
    INSTANCE;

    private S s;

    SingletonTest3() {
        s = new S();
    }

    public S getInstance() {
        return s;
    }

    class S {
        private S() {
        }
    }

    /**
     * 当通过ObjectInputputStream反序列化Java对象时，如果对象包含readResolve方法，
     * 就直接返回readResolve方法中的逻辑，否则反序列化会破坏单例模式。
     *
     * @return
     */
    private S readResolve() {
        return INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        Set<S> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(SingletonTest3.INSTANCE.getInstance());
        }
        System.out.println(set.size());
    }
}