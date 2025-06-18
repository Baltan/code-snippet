package calendar_test;

import java.util.Calendar;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/9/30 10:00
 */
public class Test5 {
    public static void main(String[] args) {
        Calendar end = Calendar.getInstance();
        end.set(Calendar.MONTH, Integer.valueOf("09") - 1);
        end.set(Calendar.DATE, 30);
        end.add(Calendar.DATE, 1);

        Calendar today = Calendar.getInstance();

        System.out.println(today.before(end));
    }
}
