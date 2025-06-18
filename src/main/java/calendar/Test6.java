package calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 * Description: 判断两个"yyyyMM"的时间字符串是否相差一个月
 *
 * @author Baltan
 * @date 2020-04-20 12:42
 */
public class Test6 {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String prev = "201912";
        String curr = "202001";
        Calendar prevCalendar = Calendar.getInstance();
        prevCalendar.setTime(format.parse(prev));
        prevCalendar.add(Calendar.MONTH, 1);
        Calendar currCalendar = Calendar.getInstance();
        currCalendar.setTime(format.parse(curr));
        System.out.println(Objects.equals(prevCalendar, currCalendar));
    }
}
