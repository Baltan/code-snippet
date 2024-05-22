package hutool_test;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * Description: 格林威治时间转当前时区时间
 *
 * @author baltan
 * @date 2024/5/21 14:34
 */
public class DateTimeTest {
    public static void main(String[] args) {
        SimpleDateFormat sdf = DateUtil.newSimpleFormat(DatePattern.UTC_PATTERN, null, TimeZone.getTimeZone("GMT"));
        System.out.println(DateUtil.parse("2024-05-10T09:08:51Z", sdf).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
    }
}
