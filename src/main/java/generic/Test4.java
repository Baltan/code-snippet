package generic;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/4/16 21:01
 */
public class Test4 {

    public static void main(String[] args) {
        List<Integer> test1 = Arrays.asList(1, 2, 3);
        List<String> test2 = Arrays.asList("one", "two", "three");
        List<Object> test3 = Arrays.asList(1, "two", 1.23);
        List<Animal> test4 = Arrays.asList(new Cat(), new Dog());
//        printList1(test1); // 编译错误
//        printList1(test2); // 编译错误
        printList1(test3);
//        printList1(test4); // 编译错误

        System.out.println("==============================================");

        printList2(test1);
        printList2(test2);
        printList2(test3);
        printList2(test4);

        System.out.println("==============================================");

        printList3(test1);
        printList3(test2);
        printList3(test3);
        printList3(test4);
    }

    public static void printList1(List<Object> list) {
        for (Object elem : list) {
            System.out.println(elem + " ");
        }
        System.out.println("**********************************************");
    }

    public static <T> void printList2(List<T> list) {
        for (T elem : list) {
            System.out.println(elem + " ");
        }
        System.out.println("**********************************************");
    }

    public static void printList3(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) + " ");
        }
        System.out.println("**********************************************");
    }
}
