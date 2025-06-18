package schedule;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/4/3 14:23
 */
public class TimerTaskTest {
    public static void main(String[] args) {
        TimerTask p = new Person();
        long delayTime = 3 * 1000L;
        long periodTime = 2 * 1000L;
        Timer timer = new Timer();
        timer.schedule(p, delayTime, periodTime);
    }
}
