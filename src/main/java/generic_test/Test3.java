package generic_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/4/16 20:49
 */
public class Test3<T> {
    private List<T> list = new ArrayList<>();

    public List<T> getList() {
        return list;
    }

    public static void main(String[] args) {
        Test3<String> t = new Test3<>();

        t.getList().add("haha");
        t.getList().add("hehe");

        System.out.println(t.getList());
    }
}
