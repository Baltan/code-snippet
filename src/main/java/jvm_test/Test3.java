package jvm_test;

/**
 * Description: 查看ClassLoader类的层次结构
 *
 * Reference: http://www.codeceo.com/article/java-classloader.html
 *
 * @author Baltan
 * @date 2019-05-30 18:30
 */
public class Test3 {
    public static void main(String[] args) {
        ClassLoader loader = Test3.class.getClassLoader();

        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }
    }
}
