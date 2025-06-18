package joda_time;

import org.joda.time.DateTime;

public class Test1 {
    public static void main(String[] args) {
        DateTime dt = new DateTime();
        int year = dt.getYear();
        int month = dt.getMonthOfYear();
        int day = dt.getDayOfMonth();
        int hour = dt.getHourOfDay();
        int minute = dt.getMinuteOfHour();
        int second = dt.getSecondOfMinute();
        int millisecond = dt.getMillisOfSecond();
        int getDayOfYear = dt.getDayOfYear();
        System.out.println(dt);
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
        System.out.println(millisecond);
        System.out.println(getDayOfYear);
    }
}
