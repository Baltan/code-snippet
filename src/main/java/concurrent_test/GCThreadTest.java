package concurrent_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/6 17:15
 */
public class GCThreadTest {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println(i);
            new Demo();
        }
    }

    static class Demo {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("正在垃圾回收……");
        }
    }
}
