package extends_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/13 11:49
 */
public interface E {
    default void doThthing() {
//        fuckWorld();
        System.out.println("这是一个默认方法……");
    }

    // JDK1.9支持私有接口方法，可供默认方法调用
//    private void fuckWorld() {
//        System.out.println("Fuck world!");
//    }
}
