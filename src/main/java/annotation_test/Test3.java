package annotation_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/29 13:37
 */
@MyAnnotation(name = "Baltan", age = 26, nationality = "China")
@MyAnnotation(name = "Dewu", age = 28, nationality = "China")
public class Test3 {
    public static void main(String[] args) {
        Class clazz = Test3.class;
        MyAnnotation[] annotations = (MyAnnotation[]) clazz.getAnnotationsByType(MyAnnotation.class);

        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i].name() + "今年" + annotations[i].age() + "岁了。");
        }
    }
}
