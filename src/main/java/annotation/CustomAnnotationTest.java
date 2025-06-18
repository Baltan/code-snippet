package annotation_test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-05-10 09:12
 */
@MyTestClass
public class CustomAnnotationTest {
    @MyBefore
    public void beforeTest1() {
        System.out.println("beforeTest1");
    }

    @MyBefore
    public void beforeTest2() {
        System.out.println("beforeTest2");
    }

    @MyTest
    public void test1() {
        System.out.println("test1");
    }

    @MyTest
    public void test2() {
        System.out.println("test2");
    }

    @MyAfter
    public void afterTest1() {
        System.out.println("afterTest1");
    }

    @MyAfter
    public void afterTest2() {
        System.out.println("afterTest2");
    }

    public static void main(String[] args) throws Exception {
        Class<CustomAnnotationTest> klass = CustomAnnotationTest.class;
        CustomAnnotationTest instance = klass.newInstance();
        if (klass.isAnnotationPresent(MyTestClass.class)) {
            List<Method> beforeMethodList = new ArrayList<>();
            List<Method> afterMethodList = new ArrayList<>();
            List<Method> testMethodList = new ArrayList<>();
            Method[] methods = klass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(MyBefore.class)) {
                    beforeMethodList.add(method);
                } else if (method.isAnnotationPresent(MyTest.class)) {
                    testMethodList.add(method);
                } else if (method.isAnnotationPresent(MyAfter.class)) {
                    afterMethodList.add(method);
                }
            }


            for (Method method : testMethodList) {
                for (Method beforeMethod : beforeMethodList) {
                    beforeMethod.setAccessible(true);
                    beforeMethod.invoke(instance);
                }

                method.setAccessible(true);
                method.invoke(instance);

                for (Method afterMethod : afterMethodList) {
                    afterMethod.setAccessible(true);
                    afterMethod.invoke(instance);
                }
            }
        }
    }
}
