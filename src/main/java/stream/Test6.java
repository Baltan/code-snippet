package stream;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/27 10:21
 */
public class Test6 {
    public static void main(String[] args) {
        List<Person> personList = Lists.newArrayList(new Person("张三", 20), new Person("李四", 25),
                new Person("王五", 23), new Person("赵六", 27), new Person("田七", 30));
        personList.stream().filter(x -> x.getAge() > 23).collect(Collectors.toList()).stream().forEach(System
                .out::println);
        System.out.println("--------------------------------------------");

        personList.stream().map(Person::getName).collect(Collectors.toCollection(LinkedList::new)).stream()
                .forEach(System.out::println);
        System.out.println("--------------------------------------------");

        System.out.println(personList.stream().collect(Collectors.counting()));
        System.out.println("--------------------------------------------");

        System.out.println(personList.stream().collect(Collectors.summingDouble(Person::getAge)));
        System.out.println("--------------------------------------------");

        System.out.println(personList.stream().collect(Collectors.averagingDouble(Person::getAge)));
        System.out.println("--------------------------------------------");

        System.out.println(personList.stream().map(Person::getAge).collect(Collectors.maxBy(
                Comparator.comparingInt(x -> x))).get());
        System.out
                .println(personList.stream().map(Person::getAge).max(Comparator.comparingInt(x -> x)).get());
        System.out.println("--------------------------------------------");

        DoubleSummaryStatistics statistics =
                personList.stream().collect(Collectors.summarizingDouble(Person::getAge));
        System.out.println(statistics.getCount());
        System.out.println(statistics.getSum());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println("--------------------------------------------");

        System.out.println(personList.stream().collect(Collectors.groupingBy((Person p) -> {
            if (p.getAge() > 25) {
                return "大于25岁";
            } else {
                return "不足25岁";
            }
        })));
        System.out.println("--------------------------------------------");

        System.out.println(personList.stream().collect(Collectors.groupingBy(Person::getAge)));
        System.out.println("--------------------------------------------");

        System.out.println(personList.stream().collect(Collectors.groupingBy(Person::getAge, Collectors
                .groupingBy(Person::getName))));
        System.out.println("--------------------------------------------");

        System.out.println(personList.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 25)));
        System.out.println("--------------------------------------------");

        System.out.println(personList.stream().map(Person::getName).collect(Collectors.joining("-")));
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
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
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
