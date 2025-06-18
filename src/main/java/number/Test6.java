package number_test;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * Description:
 *
 * @author Baltan
 * @date 2023/6/10 19:06
 * @see <a href="https://mp.weixin.qq.com/s?__biz=Mzg3Mzg0MTM1MA==&mid=2247489516&idx=2&sn=4770e36e44fb33ff108d77e15e3da085&chksm=ced88045f9af09535f0e55a2aea47409153d5bf9fd0bddecaa1215d359636bb92810ff5cd5bb&cur_album_id=2654569955987898369&scene=189#wechat_redirect"></a>
 */
public class Test6 {
    @SneakyThrows
    public static void main(String[] args) {
        /**
         * {@link java.lang.Integer.IntegerCache}
         */
        Class clazz = Integer.class.getDeclaredClasses()[0];
        Field cache = clazz.getDeclaredField("cache");
        cache.setAccessible(true);
        Integer[] cachedIntegers = (Integer[]) cache.get(clazz);
        cachedIntegers[132] = cachedIntegers[133];

        int a = 2;
        int b = a + a;
        System.out.println(a + a);
        System.out.println(b);
        /**
         * debugger {@link java.lang.Integer#valueOf(int)}
         */
        System.out.printf("%d + %d = %d%n", a, a, b);
    }
}
