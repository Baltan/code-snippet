package calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/9/28 15:21
 */
public class Test4 {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, 0);
        Date date = calendar.getTime();
        String time = new SimpleDateFormat("yyyyMMdd").format(date);
        System.out.println(time);
    }
}
