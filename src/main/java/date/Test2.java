package date_test;

import java.time.LocalDate;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/25 17:34
 */
public class Test2 {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now);

        LocalDate before = now.minusDays(1000);
        System.out.println(before);

        LocalDate after = now.plusDays(1000);
        System.out.println(after);
    }
}
