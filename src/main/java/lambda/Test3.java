package lambda_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/5/1 18:41
 */
public class Test3 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(j);
                }
            });
            thread.start();
        }
    }
}
