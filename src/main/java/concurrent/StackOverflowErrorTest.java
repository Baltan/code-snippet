package concurrent;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/5/2 10:15
 */
public class StackOverflowErrorTest {
    public static void main(String[] args) { //java.lang.StackOverflowError
        new Thread(new Runnable() {

            @Override
            public void run() {
                loop(0);
            }

            private void loop(int i) {
                if (i != 200000) {
                    i++;
                    loop(i); // 递归压栈
                } else {
                    return;
                }
            }
        }).start();
    }
}
