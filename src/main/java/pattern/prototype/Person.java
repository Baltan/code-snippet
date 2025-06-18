package pattern.prototype;

import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 11:24
 */
public class Person implements Cloneable {
    private String name;
    private Integer age;
    private LocalDateTime birthday;
    private Account account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", account=" + account +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        /**
         * 实现深拷贝
         */
        Person person = (Person) super.clone();
        person.setAccount((Account) account.clone());
        return person;
    }
}
