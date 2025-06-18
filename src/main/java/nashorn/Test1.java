package nashorn_test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Description: Reference: http://www.codeceo.com/article/nashorn-scripting-engine-tutorial.html
 *
 * @author Baltan
 * @date 2019-04-04 22:01
 */
public class Test1 {
    public static void main(String[] args) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello World!');");
    }
}
