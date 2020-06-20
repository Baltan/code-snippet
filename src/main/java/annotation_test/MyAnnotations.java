package annotation_test;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/29 12:57
 */

@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MyAnnotations {

    MyAnnotation[] value();
}
