package concurrent;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/4/19 22:55
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        Random random = new Random();

//        List<Integer> list1 = Collections.synchronizedList(new ArrayList<>());//ConcurrentModificationException
        // 适合写入很少，读取很多的情况
        CopyOnWriteArrayList<Integer> list1 = new CopyOnWriteArrayList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);

        Iterator<Integer> iterator = list1.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list1.add(random.nextInt(10));
            System.out.println("size:" + list1.size());
            System.out.println(list1);
        }
    }
}
