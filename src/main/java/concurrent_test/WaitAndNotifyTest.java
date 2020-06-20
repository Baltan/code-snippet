package concurrent_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/1/29 15:46
 */
public class WaitAndNotifyTest {
    public static void main(String[] args) {
        Object obj = new Object();
        WaitAndNotifyTest t = new WaitAndNotifyTest();
        Thread t1 = t.new Thread1(obj);
        Thread t2 = t.new Thread2(obj);
        t1.start();
        t2.start();
    }

    class Thread1 extends Thread {
        private Object o;

        public Thread1(Object o) {
            super();
            this.o = o;
        }

        public void run() {
            for (int i = 1; i <= 50; i++) {
                //线程休眠10ms
                try {
                    sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (o) {//拿走o的钥匙
                    System.out.println(getName() + ":" + i);
                    if (i == 20) {
                        try {
                            System.out.println("我去休息一下！");
                            //暂停当前线程，将当前线程加入到o对象的线程等待池中，同时将钥匙还给o对象
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }//把钥匙还给对象o
            }
        }
    }

    class Thread2 extends Thread {
        private Object o;

        public Thread2(Object o) {
            super();
            this.o = o;
        }

        public void run() {
            for (int i = 1; i <= 50; i++) {
                //线程休眠10ms
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o) {//拿走o对象钥匙
                    System.out.println(getName() + ":" + i);
                    if (i == 30) {
                        System.out.println("兄弟，快醒醒!");
                        //notify():唤醒o对象线程等待池的单个等待线程
                        o.notifyAll();
                    }
                }
            }
        }
    }
}
