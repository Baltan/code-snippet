package return_finnaly;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/3/11 23:08
 */
public class Test4 {
    public static void main(String[] args) {
        System.out.println(test3());
    }

    public static int test3() {
        int b = 20;
        try {
            System.out.println("try block");
            return b += 80;
        } catch (Exception e) {
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");
            if (b > 25) {
                System.out.println("b>25, b = " + b);
            }
            b = 150;
        }
        return 2000;
    }
}
