package generic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Description: 参考：https://zhuanlan.zhihu.com/p/79162771
 *
 * @author Baltan
 * @date 2019-08-21 22:09
 */
public class Test5 {
    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> l2 = Arrays.asList(2, 3, 4, 5, 6);
        List<Double> l3 = Arrays.asList(0.1, 0.2, 0.3, 0.4, 0.5);
        List<Dog> l4 = Arrays.asList(new Dog(), new Dog(), new Dog());
        List<Animal> l5 = Arrays.asList(new Animal(), new Animal(), new Animal());

        print1(l1, l2);
        print1(l1, l3);

        System.out.println("----------------------------------");

        print2(l1, l2);
//        print2(l1, l3); // 编译错误

        System.out.println("----------------------------------");

        print3(l1, l2);
//        print3(l1, l3); // 编译错误

        System.out.println("----------------------------------");

        print4(l1, l2);
//        print4(l1, l3); // 编译错误

        System.out.println("----------------------------------");

        print5(l4);
        print5(l5);
    }

    public static void print1(List<? extends Number> list1, List<? extends Number> list2) {
        System.out.println(list1);
        System.out.println(list2);
    }

    /**
     * 通过 T 确保泛型参数的一致性
     */
    public static <T extends Number> void print2(List<T> list1, List<T> list2) {
        System.out.println(list1);
        System.out.println(list2);
    }

    public static <T> void print3(List<T> list1, List<T> list2) {
        System.out.println(list1);
        System.out.println(list2);
    }

    /**
     * 类型参数T可以多重限定，通配符?不行
     */
    public static <T extends Number & Serializable & Comparable> void print4(List<T> list1, List<T> list2) {
        System.out.println(list1);
        System.out.println(list2);
    }

    /**
     * 通配符?可以使用超类限定，类型参数T不行，无法定义方法：
     * <p>
     * public static <T super Number> void print2(List<T> list1, List<T> list2){}
     */
    public static void print5(List<? super Dog> list) {
        System.out.println(list);
    }
}
