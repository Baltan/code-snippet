package lambda_test;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/27 22:57
 */
public class Test6 {
    public static void main(String[] args) {
        FunctionalInterfaceDemo demo = new FunctionalInterfaceDemo(() -> System.out.println("Hello"));

        demo.doSomething();
    }
}

@FunctionalInterface
interface MyInterface {
    void sayHello();
}

class FunctionalInterfaceDemo {
    private MyInterface arg;

    public FunctionalInterfaceDemo(MyInterface arg) {
        this.arg = arg;
    }

    public void doSomething() {
        arg.sayHello();
    }

    public MyInterface getArg() {
        return arg;
    }
}