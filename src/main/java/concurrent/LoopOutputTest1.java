package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 这种写法不会造成死锁
 *
 * @author Baltan
 * @date 2018/6/24 18:02
 */
public class LoopOutputTest1 {
    public static void main(String[] args) {

        Output output = new Output();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                output.outputA();
            }
            System.out.println("outputA方法歇菜了");
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                output.outputB();
            }
            System.out.println("outputB方法歇菜了");
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                output.outputC();
            }
            System.out.println("outputC方法歇菜了");
        }).start();
    }
}

class Output {
    private int num = 1;
    private int loopNum = 1;
    private Lock lock = new ReentrantLock();
    private Condition conA = lock.newCondition();
    private Condition conB = lock.newCondition();
    private Condition conC = lock.newCondition();

    public void outputA() {
        lock.lock();
        try {
            if (num != 1) {
                System.out.println("num = " + num + "-------conA等待");
                conA.await();
            }
            System.out.println("第" + loopNum + "轮循环：A");
            num = 2;
            System.out.println("num = " + num + "-------conB唤醒");
            conB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void outputB() {
        lock.lock();
        try {
            if (num != 2) {
                System.out.println("num = " + num + "-------conB等待");
                conB.await();
            }
            System.out.println("第" + loopNum + "轮循环：B");
            num = 3;
            System.out.println("num = " + num + "-------conC唤醒");
            conC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void outputC() {
        lock.lock();
        try {
            if (num != 3) {
                System.out.println("num = " + num + "-------conC等待");
                conC.await();
            }
            System.out.println("第" + loopNum + "轮循环：C");
            loopNum++;
            num = 1;
            System.out.println("num = " + num + "-------conA唤醒");
            conA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}