package puzzle;

/**
 * Description: https://developer.aliyun.com/article/705658
 *
 * @author Baltan
 * @date 2019-07-09 20:47
 */
public class SwitchTest {
    public static void main(String[] args) {
        String param = null;

        switch (param) {
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }
}
