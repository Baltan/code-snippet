package calendar_test;

import java.util.Calendar;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/19 10:36
 */
public class Test2 {
    public static void main(String[] args) {

        Calendar start = Calendar.getInstance();
        start.set(Calendar.MONTH, 8);
        start.set(Calendar.DATE, 30);

        Calendar end = Calendar.getInstance();
        end.set(Calendar.MONTH, 9);
        end.set(Calendar.DATE, 1);

        Calendar now = Calendar.getInstance();

        System.out.println(start.before(now));
        System.out.println(end.after(now));

    }
}
