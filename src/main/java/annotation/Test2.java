package annotation_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2017/12/27 09:20
 */
public class Test2 {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("zhangsan");
        p.setAge(20);
        p.setNationality("China");

        if (p.getClass().isAnnotationPresent(MyAnnotation.class)) {
            System.out.println("p带有注解");
        } else {
            System.out.println("p没有注解");
        }
    }
}
