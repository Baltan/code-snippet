package collection_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/10/12 14:05
 * @see <a href="https://mp.weixin.qq.com/s/4WDcOU13rO-K1zXPaulDjA">ArrayList中的 subList 强转 ArrayList 导致异常</a>
 */
public class Test14 {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>() {{
            add("one");
            add("two");
            add("three");
        }};
        List<String> subList1 = list1.subList(0, 1);
        /**
         * subList只是list的一个视图，修改subList会反映到原来的list上
         */
        subList1.add("x");
        System.out.println(subList1);
        System.out.println(list1);

        System.out.println("------------------------------------");

        List<String> list2 = new ArrayList<String>() {{
            add("one");
            add("two");
            add("three");
        }};
        List<String> subList2 = list2.subList(0, 1);
        /**
         * 修改list会导致ConcurrentModificationException
         * @see ArrayList.SubList#checkForComodification()
         * 此时，ArrayList.this.modCount == 4，this.modCount == 3，抛出ConcurrentModificationException异常
         */
        list2.add("x");
        System.out.println(subList2);
        System.out.println(list2);
    }
}
