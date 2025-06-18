package jodd;

import jodd.bean.BeanUtil;
import jodd.bean.BeanUtilBean;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/11/6 09:40
 */
public class Test3 {
    public static void main(String[] args) {
        Person person = new Person();
        BeanUtil beanUtil = new BeanUtilBean();
        beanUtil.setProperty(person, "name", "Dick");
        System.out.println((String) beanUtil.getProperty(person, "name"));
        System.out.println(person.getName());
    }
}

class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
