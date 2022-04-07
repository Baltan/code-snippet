package instanceof_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/7 17:37
 */
public class Test1 {
    public static void main(String[] args) {
        Teacher p1 = new Teacher();
        Student p2 = new Student();
        say(p1);
        say(p2);
    }

    public static abstract class Person {
        abstract void say();
    }

    public static class Teacher extends Person {
        @Override
        public void say() {
            System.out.println("Teacher~~~~~~");
        }
    }

    public static class Student extends Person {
        @Override
        public void say() {
            System.out.println("Student~~~~~~");
        }
    }

    /**
     * @param p
     * @see
     * <a href="https://www.pdai.tech/md/java/java8up/java14.html#jep-305-instanceof-%E6%A8%A1%E5%BC%8F%E5%8C%B9%E9%85%8D%E9%A2%84%E8%A7%88%E9%98%B6%E6%AE%B5">JEP 305: instanceof 模式匹配（预览阶段）</a>
     */
    public static void say(Object p) {
        if (p instanceof Student student) {
            student.say();
        } else if (p instanceof Teacher teacher) {
            teacher.say();
        }
    }
}
