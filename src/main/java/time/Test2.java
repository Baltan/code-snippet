package time;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/3/9 08:41
 */
public class Test2 {
    public static void main(String[] args) {
        long s = System.nanoTime();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.nanoTime() - s);
    }
}
