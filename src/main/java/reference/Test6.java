package reference;

/**
 * Description: reference: http://www.codeceo.com/article/java-gc-part-one.html
 * <p>
 * 在可达性分析法中，判定一个对象obj是否可回收，至少要经历两次标记过程：
 * <p>
 * 1、如果对象obj到 GC Roots没有引用链，则进行第一次标记。
 * 2、如果对象obj重写了finalize()方法，且还未执行过，那么obj会被插入到F-Queue队列中，由一个虚拟机自动创建的、低优先级的Finalizer线程触发其finalize()
 * 方法。finalize()方法是对象逃脱死亡的最后机会，GC会对队列中的对象进行第二次标记，如果obj在finalize()
 * 方法中与引用链上的任何一个对象建立联系，那么在第二次标记时，objA会被移出“即将回收”集合。
 *
 * @author Baltan
 * @date 2019-03-21 09:11
 */
public class Test6 {
    public static Test6 object;

    public void isAlive() {
        System.out.println("I'm alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("method finalize is running");
        object = this;
    }


    public static void main(String[] args) throws Exception {
        object = new Test6();

        // 第一次执行，finalize方法会自救
        object = null;
        System.gc();

        Thread.sleep(500);
        if (object != null) {
            object.isAlive();
        } else {
            System.out.println("I'm dead");
        }

        // 第二次执行，finalize方法已经执行过
        object = null;
        System.gc();

        Thread.sleep(500);
        if (object != null) {
            object.isAlive();
        } else {
            System.out.println("I'm dead");
        }
    }
}