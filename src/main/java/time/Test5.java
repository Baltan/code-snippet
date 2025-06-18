package time_test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-04-15 17:17
 */
public class Test5 {
    public static void main(String[] args) {
        LocalDate graduatedDate1 = LocalDate.parse("20180909", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate graduatedDate2 = LocalDate.parse("20080909", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate graduatedDate3 = LocalDate.parse("20200909", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate graduatedDate4 = LocalDate.parse("20190415", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate graduatedDate5 = LocalDate.parse("20190416", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate graduatedDate6 = LocalDate.parse("20130415", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate graduatedDate7 = LocalDate.parse("20130414", DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(graduatedDate1.isBefore(LocalDate.now()));
        System.out.println(graduatedDate2.isBefore(LocalDate.now()));
        System.out.println(graduatedDate3.isBefore(LocalDate.now()));

        LocalDate now = LocalDate.now();
        long duration1 =  ChronoUnit.YEARS.between(graduatedDate1, now);
        long duration2 = ChronoUnit.YEARS.between(graduatedDate2, now);
        long duration3 = ChronoUnit.YEARS.between(graduatedDate3, now);
        long duration4 = ChronoUnit.YEARS.between(graduatedDate4, now);
        long duration5 = ChronoUnit.YEARS.between(graduatedDate5, now);
        long duration6 = ChronoUnit.YEARS.between(graduatedDate6, now);
        long duration7 = ChronoUnit.YEARS.between(graduatedDate7, now);
        System.out.println(duration1 >= 1 && duration1 <= 7);
        System.out.println(duration2 >= 1 && duration2 <= 7);
        System.out.println(duration3 >= 1 && duration3 <= 7);
        System.out.println(duration4 >= 1 && duration4 <= 7);
        System.out.println(duration5 >= 1 && duration5 <= 7);
        System.out.println(duration6 >= 1 && duration6 <= 7);
        System.out.println(duration7 >= 1 && duration7 <= 7);
    }
}
