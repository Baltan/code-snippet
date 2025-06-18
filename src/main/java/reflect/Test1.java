package reflect_test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-11 18:54
 */
public class Test1 {
    public static void main(String[] args)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
        Class<T> clazz = T.class;
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        T t = constructor.newInstance();
        System.out.println(t.getClass());
    }
}

class T {
    private T() {
    }
}
