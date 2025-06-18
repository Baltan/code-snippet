package time_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/3/9 08:44
 */
public class Test3 {
    public static void main(String[] args) {
        long s= System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-s);
    }
}
