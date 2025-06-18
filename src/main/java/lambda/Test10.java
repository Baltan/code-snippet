package lambda;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/19 11:37
 */
public class Test10 {
    public static void main(String[] args) {

        List<Loser> list = Lists.newArrayList(new Loser(40), new Loser(20), new Loser(30));

        System.out.println(list);
        Collections.sort(list, Comparator.comparing(Loser::getAge));
        System.out.println(list);
//        list.forEach(System.out::println);
    }
}

class Loser {
    private int age;

    public Loser(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Loser{" +
                "age=" + age +
                '}';
    }
}
