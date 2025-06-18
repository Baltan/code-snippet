package nashorn_test;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Description: Reference: http://www.codeceo.com/article/nashorn-scripting-engine-tutorial.html
 *
 * @author Baltan
 * @date 2019-04-04 22:16
 */
public class Test3 {
    public static void main(String[] args) throws FileNotFoundException, ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("src/nashorn_test/script.js"));
    }

    public static String fun1(String name) {
        System.out.format("Hi there from Java, %s", name);
        return "greetings from java";
    }

    /**
     * 测试引擎如何处理类型转换，使用不同JavaScript类型来调用java方法
     *
     * @param object
     */
    public static void fun2(Object object) {
        System.out.println(object.getClass());
        System.out.println();
    }

    /**
     * 更改参数类型Object为ScriptObjectMirror，能获取到传入JavaScript中对象的一些信息
     *
     * @param mirror
     */
    public static void fun3(ScriptObjectMirror mirror) {
        System.out.println(mirror.getClassName() + ": " + Arrays.toString(mirror.getOwnKeys(true)));
        System.out.println(mirror.getOwnPropertyDescriptor("foo"));
        System.out.println(mirror.getOwnPropertyDescriptor("bar"));
        System.out.println(mirror.getProto());
        System.out.println();
    }

    /**
     * 调用script.js的Person对象的getFullName()函数
     *
     * @param person
     */
    public static void fun4(ScriptObjectMirror person) {
        System.out.println("Full Name is: " + person.callMember("getFullName"));
    }
}
