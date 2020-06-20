package annotation_test;


import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.*;

@Repeatable(MyAnnotations.class)
@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MyAnnotation {

    String name() default "Baltan";

    int age() default 27;

    String nationality() default "China";
}
