package date_test;

import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/11/19 15:27
 */
public class Test3 {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getMonth());
        System.out.println(now.getMonth().getValue());
        System.out.println(now.toLocalDate());
    }
}
