package system;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/9/13 09:23
 */
public class Test5 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            final int FINAL_I = i;
            try {
                Runtime.getRuntime().addShutdownHook(
                        new Thread(() -> {
                            if (FINAL_I == 4) {
                                System.out.println(
                                        "Inside Try Block.Exiting without executing Finally block.");
                                System.exit(0);
                            }
                        }));
            } finally {
                System.out.println("Inside Finally Block.");
            }
        }
    }
}
