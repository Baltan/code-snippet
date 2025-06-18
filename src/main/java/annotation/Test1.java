package annotation;

@MyAnnotation(name = "Baltan", age = 26, nationality = "China")
public class Test1 {
    public static void main(String[] args) {
        MyAnnotation annotation = Test1.class.getAnnotation(MyAnnotation.class);
        String name = annotation.name();
        int age = annotation.age();
        String nationality = annotation.nationality();
        System.out.println("I'm " + name + ",at the age of " + age + ",and I come from " + nationality + ".");
    }
}
