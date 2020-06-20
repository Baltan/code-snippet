package extends_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/8/10 17:31
 */
public class B extends A {
    public void printNum(int num) {
        System.out.println("int-" + num);
    }

    public void printNum(float num) {
        System.out.println("float-" + num);
    }

    public void printNum(double num) {
        System.out.println("double-" + num);
    }
}
