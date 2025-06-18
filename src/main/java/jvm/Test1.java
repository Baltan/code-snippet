package jvm;

/**
 * Description:
 * <p>
 * -XX:+UseSerialGC：设置串行垃圾收集器
 * -XX:+UseParallelGC：设置并行垃圾收集器
 * -XX:+UseConcMarkSweepGC：设置并发垃圾收集器
 *
 * <p>
 * -XX:+PrintGC: 打印垃圾回收信息
 * -XX:+PrintGCDetails: 打印垃圾回收详细信息
 * -Xloggc:filename：将垃圾回收信息记录到日志文件里
 * -XX:+DisableExplicitGC：禁止显示调用System.gc()
 *
 * <p>
 * -XX:+DoEscapeAnalysis: 开启逃逸分析
 * -XX:+EliminateAllocations: 开启标量替换
 * -XX:+UseTLAB: 开启线程本地缓存
 *
 * <p>
 * OutOfMemoryError
 * -Xms:堆内存初始空间大小
 * -Xmx:堆内存最大空间大小
 * <p>
 * StackOverFlowError
 * -Xss:栈内存初始空间大小，该值较小时，并发数可以较大（划分出较多栈空间，每个线程占有一块栈空间）；该值较大时，递归次数可以较多（每个栈空间中可以启用较多栈帧，每次递归调用方法启用一个栈帧）
 * -Xsx:栈内存最大空间大小
 *
 * @author Baltan
 * @date 2018/7/19 23:15
 */
public class Test1 {

    class User {
        private String name;
        private int id;

        private User(String name, int id) {
            this.name = name;
            this.id = id;
        }
    }

    public void createUser(int i) {
        new User("name" + i, i);
    }

    public static void main(String[] args) {
        Test1 t = new Test1();
        long start = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            t.createUser(i);
        }
        long end = System.nanoTime();
        /**
         * 测试结果：
         * 对象分配到堆Eden区：950752303
         * 对象分配到线程本地缓存：685998284
         * 对象分配到栈上：584842380
         */
        System.out.println(end - start);
    }
}
