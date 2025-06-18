package collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-01 14:45
 */
public class Test11 {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("a", "s", "e", "q", "f", "h", "S", "E", "O", "P");
        /**
         * 忽略大小写排序
         */
        list1.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(list1);
        /**
         * 先大写、后小写排序
         */
        list1.sort(Comparator.naturalOrder());
        System.out.println(list1);

        List<Student> list2 = Arrays.asList(new Student("zhangsan", 20), new Student("lisi", 20), new Student(
                "wangwu", 21), new Student("zhaoliu", 21), new Student("tianqi", 20), new Student("zhuba",
                21));
        list2.sort(Comparator.comparingInt(Student::getAge).reversed().thenComparing(Student::getName));
        System.out.println(list2);
    }

    static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
