package optional_test;

import lombok.Data;

import java.util.Optional;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/12/6 09:57
 * @see <a href="https://mp.weixin.qq.com/s/96Tk4A7ccU6ngczS7OVBfg"></a>
 */
public class Test4 {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("张三");
        person.setAge(2);
        /**
         * 如果对象非空，则调用函数
         */
        Optional.ofNullable(person)
                .ifPresent(p -> System.out.println("年龄：" + p.getAge() + "岁"));

        System.out.println("------------------------------------------");
        /**
         * 过滤对象，如果符合条件则返回Optional对象本身，否则返回空Optional
         */
        Optional<Person> gt50 = Optional.ofNullable(person)
                .filter(p -> p.getAge() > 50);
        System.out.println(gt50);
        Optional<Person> lt10 = Optional.ofNullable(person)
                .filter(p -> p.getAge() < 10);
        System.out.println(lt10);

        System.out.println("------------------------------------------");
        /**
         * 将对象进行二次运算后的结果返回
         */
        String name = Optional.ofNullable(person)
                .map(p -> "姓名是" + person.getName()).orElse("name为空");
        System.out.println(name);

        Optional<String> nameOpt = Optional.ofNullable(person)
                .map(p -> "姓名是" + Optional.ofNullable(p.getName()).orElse("name为空"));
        System.out.println(nameOpt);
    }

    @Data
    private static class Person {
        private String name;
        private int age;
    }
}
