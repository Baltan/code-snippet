package date;

import java.sql.Date;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/4/7 22:38
 */
public class Test1 {
    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);
    }
}
