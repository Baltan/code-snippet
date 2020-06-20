package pattern_test.prototype;

import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/12/2 23:01
 */
public class Test1 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Account account = new Account();
        account.setUsername("zhangsan");
        account.setPassword("zhangsan");

        Person p1 = new Person();
        p1.setName("zhangsan");
        p1.setAge(20);
        p1.setBirthday(LocalDateTime.now());
        p1.setAccount(account);
        System.out.println("p1:" + p1);

        Person p2 = (Person) p1.clone();
        System.out.println("p2:" + p2);
        System.out.println();

        p2.setName("lisi");
        System.out.println("p1:" + p1);
        System.out.println("p2:" + p2);
        System.out.println();

        p2.setBirthday(LocalDateTime.now());
        System.out.println("p1:" + p1);
        System.out.println("p2:" + p2);
        System.out.println();

        Account account1 = p2.getAccount();
        account1.setUsername("lisi");
        account1.setPassword("lisi");
        p2.setAccount(account1);
        System.out.println("p1:" + p1);
        System.out.println("p2:" + p2);
    }
}