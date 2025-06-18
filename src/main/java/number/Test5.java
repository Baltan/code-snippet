package number;

import java.util.Random;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-15 20:10
 */
public class Test5 {
    public static void main(String[] args) {

        /**
         * 高并发情形下产生随机数应当使用ThreadLocalRandom类
         * ThreadLocalRandom rand = ThreadLocalRandom.current();
         */
        Random rand1 = new Random(1);
        System.out.println(rand1.nextInt(100));
        Random rand2 = new Random(1);
        System.out.println(rand2.nextInt(100));
        Random rand3 = new Random(1);
        System.out.println(rand3.nextInt(100));

        System.out.println("--------------------------------");

        Random rand4 = new Random();
        System.out.println(rand4.nextInt(100));
        Random rand5 = new Random();
        System.out.println(rand5.nextInt(100));
        Random rand6 = new Random();
        System.out.println(rand6.nextInt(100));

        System.out.println("--------------------------------");
    }
}
