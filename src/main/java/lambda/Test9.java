package lambda;

import java.util.Random;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/19 10:48
 */
public class Test9 {
    public static void main(String[] args) {
        repeatAccept(10, i -> System.out.println(i));

        System.out.println("-----------------------------");

        repeatSupply(10, () -> new Random().nextInt(100));
    }

    public static void repeatAccept(int repeatNum, IntConsumer con) {
        for (int i = 0; i < repeatNum; i++) {
            con.accept(i);
        }
    }

    public static void repeatSupply(int repeatNum, IntSupplier sup) {
        for (int i = 0; i < repeatNum; i++) {
            System.out.println(sup.getAsInt());
        }
    }
}
