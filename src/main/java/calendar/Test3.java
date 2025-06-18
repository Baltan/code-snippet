package calendar_test;

import java.util.Calendar;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/26 15:12
 */
public class Test3 {
    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        now.set(2018, 6, 27); // 设置日期为2018.7.27
//        System.out.println(now.get(Calendar.MONTH));
        now.add(Calendar.MONTH, -6); // 例如：2017.7.1-2018.6.30返回年份2017
        System.out.println(now.get(Calendar.YEAR));
    }
}
