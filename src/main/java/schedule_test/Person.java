package schedule_test;

import java.util.TimerTask;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/4/3 14:22
 */
class Person extends TimerTask {
    @Override
    public void run() {
        System.out.println("*************");
    }
}
