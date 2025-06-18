package initialize_test;


/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/20 19:59
 */
public class Test1 {
    public static void main(String[] args) {
        staticFunction();
    }

    static Test1 t = new Test1();

    static {
        System.out.println("Test1的静态代码块");
    }

    {
        System.out.println("Test1的普通代码块");
    }

    Test1() {
        System.out.println("Test1的构造方法");
        System.out.println("price=" + price + ",amount=" + amount);
    }

    public static void staticFunction() {
        System.out.println("Test1的静态方法");
    }

    int price = 110;
    static int amount = 112;
}
