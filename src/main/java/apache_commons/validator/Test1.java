package apache_commons_test.validator;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/9/26 15:28
 */
public class Test1 {
    public static void main(String[] args) {
        DateValidator dateValidator = DateValidator.getInstance();
        System.out.println(dateValidator.validate("2018-09-26", "yyyy-MM-dd"));
        System.out.println(dateValidator.validate("2018-09-26", "yyyyMMdd"));
        System.out.println(dateValidator.validate("20180926", "yyyyMMdd"));

        EmailValidator emailValidator = EmailValidator.getInstance();
        System.out.println(emailValidator.isValid("fuckme@qq.com"));
        System.out.println(emailValidator.isValid("fuckme@qqcom"));
    }
}
