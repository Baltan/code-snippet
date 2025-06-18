package nashorn_test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Description: Reference: http://www.codeceo.com/article/nashorn-scripting-engine-tutorial.html
 *
 * @author Baltan
 * @date 2019-04-04 22:08
 */
public class Test2 {
    public static void main(String[] args)
            throws FileNotFoundException, ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("src/nashorn_test/script.js"));

        /**
         * 为了调用函数，需要把脚本引擎转换为Invocable
         */
        Invocable invocable = (Invocable) engine;

        /**
         * 调用script.js的fun1()函数
         */
        Object result = invocable.invokeFunction("fun1", "Peter Parker");
        System.out.println(result);
        System.out.println(result.getClass());

        System.out.println("---------------------------------------");

        /**
         * 调用script.js的fun2()函数
         */
        invocable.invokeFunction("fun2", new Date());
        invocable.invokeFunction("fun2", LocalDateTime.now());
    }
}
