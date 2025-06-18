package nashorn_test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Description: Reference: http://www.codeceo.com/article/nashorn-scripting-engine-tutorial.html
 *
 * @author Baltan
 * @date 2019-04-04 22:43
 */
public class Test4 {
    public static void main(String[] args) throws FileNotFoundException, ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("src/nashorn_test/array_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/list_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/map_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/lambda_stream_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/java_class_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/reload_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/java_bean_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/function_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/property_bind_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/string_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/location_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/import_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/list_to_array_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/extend_test.js"));
        System.out.println();
        engine.eval(new FileReader("src/nashorn_test/underscore_test.js"));
    }
}
