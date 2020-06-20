package initialize_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/24 14:36
 */
public class Test3 {
    public static void main(String[] args) {
        new Person("Zhang San", 30) {{
            System.out.println(getName());
            System.out.println(getAge());
        }};

        System.out.println("-------------------------------");

        new Person() {{
            setName("Li Si");
            setAge(20);
            System.out.println(getName());
            System.out.println(getAge());
        }};
    }
}

class Person {
    private String name;
    private int age;

    public Person() {
        System.out.println("无参构造方法执行……");
    }

    public Person(String name, int age) {
        System.out.println("有参构造方法执行……");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    {
        System.out.println("构造代码块执行……");
    }

    static {
        System.out.println("静态代码块执行……");
    }
}
