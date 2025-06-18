package concurrent;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/2/25 13:05
 */
public class VolatileTest {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        Thread thread = new Thread(td);
        thread.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        while (true) {
            if (td.isFlag()) {
                System.out.println("flag的值已经被修改为true了。");
                break;
            }
        }
    }


}

class ThreadDemo implements Runnable {
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setFlag(true);
        System.out.println("flag=" + flag);
    }
}