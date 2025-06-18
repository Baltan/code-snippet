package lombok;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * 1、必须勾选Preferences-Build,Execution,Deployment-Compiler-Annotation Processors-Enable annotation processing，
 * 否则无法通过编译
 * 2、参考：
 * <a href="https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247485385&idx=2&sn=a7c3fb4485ffd8c019e5541e9b1580cd&chksm=cea24802f9d5c1144eee0da52cfc0cc5e8ee3590990de3bb642df4d4b2a8cd07f12dd54947b9&token=1381785723&lang=zh_CN#rd"></a>
 *
 * @author Baltan
 * @date 2019-12-01 17:00
 */
public class Test1 {
    public static void main(String[] args) {
        /**
         * @AllArgsConstructor
         */
        Person zhangsan = new Person(1, "Zhang San", 33, "male", "Teacher");
        Person anotherZhangsan = new Person(1, "Zhang San", 33, "male", "Teacher");
        Person lisi = new Person(2, "Li Si", 34, "male", "Doctor");
        /**
         * @RequiredArgsConstructor(staticName = "getInstance")
         */
        Person wangwu = Person.getInstance(3, "Wang Wu", "male", "Worker");
        /**
         * @ToString(exclude = {"id"}, includeFieldNames = false)
         */
        System.out.println(zhangsan);
        System.out.println(lisi);
        System.out.println(wangwu);

        zhangsan.say("I am Zhang San!");
        /**
         * @NonNull
         * NullPointerException: sentence is marked non-null but is null
         */
//        zhangsan.say(null);

        /**
         * @EqualsAndHashCode
         */
        Set<Person> persons = new HashSet<>();
        persons.add(zhangsan);
        persons.add(anotherZhangsan);
        persons.add(lisi);
        persons.add(wangwu);
        System.out.println(persons);
        /**
         * @Data：注解在类上，相当于同时使用了@ToString、@EqualsAndHashCode、@Getter、@Setter和@RequiredArgsConstrutor
         */
        Book cynh = new Book("草样年华", "孙睿");
        System.out.println(cynh.getName());
        System.out.println(cynh.getAuthor());
        System.out.println(cynh);
        /**
         * @Value：注解在类上，是@Data的不可变形式，相当于为属性添加final声明，只提供getter方法，而不提供setter方法
         */
        Computer mbp = new Computer("Apple", "MacBook Pro(13-inch)", 9999.99f);
        System.out.println(mbp.getBrand());
        System.out.println(mbp.getModel());
        System.out.println(mbp.getPrice());
        System.out.println(mbp);
        /**
         * @Builder：用在类、构造器、方法上，为你提供复杂的builder APIs
         */
        Desk desk = Desk.builder().length(2f).width(1.2f).height(1.1f).color("white").build();
        System.out.println(desk);
    }
}
